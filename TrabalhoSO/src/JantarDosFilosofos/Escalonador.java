package JantarDosFilosofos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;



public class Escalonador {
    public LinkedList<Filosofo> mesa = new LinkedList<Filosofo>();
    public LinkedList<Filosofo> mesaAux = new LinkedList<Filosofo>();
    public LinkedList<Filosofo> comendo = new LinkedList<Filosofo>();
    public LinkedList<Garfo> talheres = new LinkedList<Garfo>();
    public int quantum;

    public void lerArquivo(String endereco) {
        Filosofo auxF;
        Garfo auxG;
        BufferedReader br = null;
        String linha = "";
        String divisor = " ";
        String linhaG = "";

        int cont = 0;
        try {

            br = new BufferedReader(new FileReader(endereco));
            linha = br.readLine();
            quantum = Integer.parseInt(linha);
            while((linha = br.readLine()) != null) {
                String[] dados = linha.split(divisor);
                //System.out.println(dados[0]);
                auxF = new Filosofo();
                auxF.setNome(dados[0]);
                auxF.setTempoExecucao(Integer.parseInt(dados[1]));
                auxG = new Garfo();
                linhaG = "Garfo " + cont;
                auxG.setNome(linhaG);
                auxF.setGarfoDireita(auxG);
                cont++;

                mesa.add(auxF);
                talheres.add(auxG);
            }

            executaEsquerda();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void executaEsquerda() throws IOException {

        int cont;
        int contg;
        for (cont = 0; cont < mesa.size(); cont++) {
            if(mesa.getFirst() == mesa.get(cont)) {
                mesa.get(cont).setGarfoEsquerda(mesa.getLast().getGarfoDireita());
            }else {
                mesa.get(cont).setGarfoEsquerda(talheres.get(cont-1));
            }
        }
        executa();
    }

    public void executa() throws IOException {
//		FileWriter filosofosLog = new FileWriter("/media/alunos/WITT/SO/filosofosLog.txt");
        FileWriter filosofosLog = new FileWriter("C:\\Users\\ketrim\\Documents\\SO\\SO.txt");
        PrintWriter gravarFilosofosLog = new PrintWriter(filosofosLog);
        String linhaGravarFilosofosLog = "";

        int cont;
        while (!mesa.isEmpty()){
            for(Filosofo fil : mesa) {
                if(!fil.getRemoveFlag() && fil.getTempoExecucao()>0) {
                    mesaAux.add(fil);
                    linhaGravarFilosofosLog = "* Filósofo " + fil.getNome()  + " tem sua vez garantida para jantar; %n";
                    gravarFilosofosLog.printf(linhaGravarFilosofosLog);
                }
            }
            if(mesaAux.isEmpty()) {
                mesa.clear();
            }
            while(!mesaAux.isEmpty()) {
                for(Filosofo fil : mesaAux) {
                    if(fil.getGarfoDireita().getUso() == false && fil.getGarfoEsquerda().getUso() == false && fil.getTempoExecucao() > 0 && !fil.getRemoveFlag()) {
                        comendo.add(fil);
                        fil.getGarfoEsquerda().mudarUso(true);
                        fil.getGarfoDireita().mudarUso(true);
                        linhaGravarFilosofosLog = "> Filósofo " + fil.getNome()  + " toma posse dos garfos " + fil.getGarfoEsquerda().getNome() + " e " +fil.getGarfoDireita().getNome() + "; %n";
                        gravarFilosofosLog.printf(linhaGravarFilosofosLog);
                    }else {
                        linhaGravarFilosofosLog = "> Filósofo " + fil.getNome()  + " não pode comer, já que seus garfos " + fil.getGarfoEsquerda().getNome() + " e " +fil.getGarfoDireita().getNome() + " estão sendo utilizados por outro filósofo; %n";
                        gravarFilosofosLog.printf(linhaGravarFilosofosLog);
                    }
                }
                for(cont=0; cont < quantum; cont++) {
                    for(Filosofo fil : comendo) {
                        if(fil.getTempoExecucao()>0) {
                            fil.setTempoExecucao(fil.getTempoExecucao()-1);
                            linhaGravarFilosofosLog = ">> Filósofo " + fil.getNome()  + " come; %n";
                            gravarFilosofosLog.printf(linhaGravarFilosofosLog);
                            System.out.println(fil.getNome());
                        }else {
                            fil.setRemoveFlag(true);
                            mesaAux.remove(fil);
                        }
                        fil.setRemoveFlag(true);
                        mesaAux.remove(fil);
                    }

                }
                for(Filosofo fil : comendo) {
                    fil.getGarfoDireita().mudarUso(false);
                    fil.getGarfoEsquerda().mudarUso(false);
                    linhaGravarFilosofosLog = "> Filósofo " + fil.getNome()  + " larga os garfos " + fil.getGarfoEsquerda().getNome() + " e " +fil.getGarfoDireita().getNome() + " e terá que esperar que todos os filósofos restantes comam um pouco para que possa voltar a tomar posse dos garfos; %n";
                    gravarFilosofosLog.printf(linhaGravarFilosofosLog);
                }
                comendo.clear();
//				for(Filosofo fil : mesaAux) {
//					System.out.println(fil.getNome());
//				}
//				mesaAux.clear();
            }
            for(Filosofo fil : mesa) {
                if(fil.getTempoExecucao()>0) {
                    fil.setRemoveFlag(false);
                }
            }
        }

        linhaGravarFilosofosLog = "* Todos os filósofos já comeram; %n";
        gravarFilosofosLog.printf(linhaGravarFilosofosLog);
        filosofosLog.close();

    }
}
