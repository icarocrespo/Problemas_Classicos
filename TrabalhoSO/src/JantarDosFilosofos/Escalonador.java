package JantarDosFilosofos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Escalonador {

    public LinkedList<Filosofo> mesa = new LinkedList<>();
    public LinkedList<Filosofo> mesaAux = new LinkedList<>();
    public LinkedList<Filosofo> comendo = new LinkedList<>();
    public LinkedList<Garfo> talheres = new LinkedList<>();
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
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(divisor);
                auxF = new Filosofo();
                auxF.setNome(dados[0]);
                auxF.setTempo(Integer.parseInt(dados[1]));
                auxG = new Garfo();
                linhaG = "Garfo " + cont;
                auxG.setNome(linhaG);
                auxF.setG_direita(auxG);
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
            if (mesa.getFirst() == mesa.get(cont)) {
                mesa.get(cont).setG_esquerda(mesa.getLast().getG_direita());
            } else {
                mesa.get(cont).setG_esquerda(talheres.get(cont - 1));
            }
        }
        executa();
    }

    public void executa() throws IOException {
        FileWriter filosofosLog = new FileWriter("C:\\Users\\ketrim\\Documents\\SO\\SO.txt");
        PrintWriter gravarFilosofosLog = new PrintWriter(filosofosLog);
        String linhaGravarFilosofosLog = "";

        int cont;

        while (!mesa.isEmpty()) {
            for (Filosofo fil : mesa) {
                if (!fil.getFlag() && fil.getTempo() > 0) {
                    mesaAux.add(fil);
                    linhaGravarFilosofosLog = "* Filósofo " + fil.getNome() + " tem sua vez garantida para jantar; %n";
                    gravarFilosofosLog.printf(linhaGravarFilosofosLog);
                }
            }
            if (mesaAux.isEmpty()) {
                mesa.clear();
            }
            while (!mesaAux.isEmpty()) {
                for (Filosofo fil : mesaAux) {
                    if (fil.getG_direita().getEmUso() == false && fil.getG_esquerda().getEmUso() == false && fil.getTempo() > 0 && !fil.getFlag()) {
                        comendo.add(fil);
                        fil.getG_esquerda().setEmUso(true);
                        fil.getG_direita().setEmUso(true);
                        linhaGravarFilosofosLog = "> Filósofo " + fil.getNome() + " toma posse dos garfos " + fil.getG_esquerda().getNome() + " e " + fil.getG_direita().getNome() + "; %n";
                        gravarFilosofosLog.printf(linhaGravarFilosofosLog);
                    } else {
                        linhaGravarFilosofosLog = "> Filósofo " + fil.getNome() + " não pode comer, já que seus garfos " + fil.getG_esquerda().getNome() + " e " + fil.getG_direita().getNome() + " estão sendo utilizados por outro filósofo; %n";
                        gravarFilosofosLog.printf(linhaGravarFilosofosLog);
                    }
                }
                for (cont = 0; cont < quantum; cont++) {
                    for (Filosofo fil : comendo) {
                        if (fil.getTempo() > 0) {
                            fil.setTempo(fil.getTempo() - 1);
                            linhaGravarFilosofosLog = ">> Filósofo " + fil.getNome() + " come; %n";
                            gravarFilosofosLog.printf(linhaGravarFilosofosLog);
                            System.out.println(fil.getNome());
                        } else {
                            fil.setFlag(true);
                            mesaAux.remove(fil);
                        }
                        fil.setFlag(true);
                        mesaAux.remove(fil);
                    }

                }
                for (Filosofo fil : comendo) {
                    fil.getG_direita().setEmUso(false);
                    fil.getG_esquerda().setEmUso(false);
                    linhaGravarFilosofosLog = "> Filósofo " + fil.getNome() + " larga os garfos " + fil.getG_esquerda().getNome()
                            + " e " + fil.getG_direita().getNome()
                            + " e terá que esperar que todos os filósofos restantes comam um pouco para que possa voltar a tomar posse dos garfos; %n";
                    gravarFilosofosLog.printf(linhaGravarFilosofosLog);
                }
                comendo.clear();
            }
            for (Filosofo fil : mesa) {
                if (fil.getTempo() > 0) {
                    fil.setFlag(false);
                }
            }
        }
        linhaGravarFilosofosLog = "* Todos os filósofos já comeram; %n";
        gravarFilosofosLog.printf(linhaGravarFilosofosLog);
        filosofosLog.close();

    }
}
