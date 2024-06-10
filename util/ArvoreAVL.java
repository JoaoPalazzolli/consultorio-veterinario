package util;

import models.Pet;

public class ArvoreAVL<T extends Comparable<T>> {
    private NosArvore<T> raiz;
    private Integer tamanho;

    public ArvoreAVL() {
        this.raiz = null;
        this.tamanho = 0;
    }

    public NosArvore<T> getRaiz() {
        return raiz;
    }

    public int getAltura(NosArvore<T> nos) {
        if (nos == null) {
            return 0;
        }
        return nos.getAltura();
    }

    public int getBalanceamento(NosArvore<T> nos) {
        if (nos == null) {
            return 0;
        }
        return getAltura(nos.getEsquerda()) - getAltura(nos.getDireita());
    }

    public void adicionarValor(T valor) {
        this.raiz = adicionarBalancearValor(raiz, valor);
        this.tamanho++;
    }

    public void removerValor(T valor) {
        this.raiz = removerBalancearValor(raiz, valor);
        this.tamanho--;
    }

    public T buscarValor(String nome){
        var current = this.raiz;

        while(current != null && !((Pet) current.getValor()).getNome().equals(nome)){
            if(nome.compareTo(((Pet) current.getValor()).getNome()) < 0){
                current = current.getEsquerda();
            } else {
                current = current.getDireita();
            }
        }

        if(current == null){
            return null;
        }

        return current.getValor();
    }

    public void emOrdem(NosArvore<T> current) {
        if (current != null) {
            emOrdem(current.getEsquerda());
            System.out.print(current.getValor() + " ");
            emOrdem(current.getDireita());
        }
    }

    public void preOrdem(NosArvore<T> current) {
        if (current != null) {
            System.out.print(current.getValor() + " ");
            preOrdem(current.getEsquerda());
            preOrdem(current.getDireita());
        }
    }

    public void posOrdem(NosArvore<T> current) {
        if (current != null) {
            posOrdem(current.getEsquerda());
            posOrdem(current.getDireita());
            System.out.print(current.getValor() + " ");
        }
    }

    private NosArvore<T> removerBalancearValor(NosArvore<T> nos, T valor) {
        if (nos == null) {
            return nos;
        }

        if (valor.compareTo(nos.getValor()) < 0) {
            nos.setEsquerda(removerBalancearValor(nos.getEsquerda(), valor));
        } else if (valor.compareTo(nos.getValor()) > 0) {
            nos.setDireita(removerBalancearValor(nos.getDireita(), valor));
        } else {
            if (nos.getEsquerda() == null) {
                return nos.getDireita();
            } else if (nos.getDireita() == null) {
                return nos.getEsquerda();
            }

            NosArvore<T> valorMinimo = getValorMinimo(nos.getDireita());

            nos.setValor(valorMinimo.getValor());

            nos.setDireita(removerBalancearValor(nos.getDireita(), valorMinimo.getValor()));
        }

        nos.setAltura(1 + Math.max(getAltura(nos.getEsquerda()), getAltura(nos.getDireita())));

        int balanceamento = getBalanceamento(nos);

        if (balanceamento > 1 && getBalanceamento(nos.getEsquerda()) >= 0) {
            return rotacaoDireita(nos);
        }
        if (balanceamento > 1 && getBalanceamento(nos.getEsquerda()) < 0) {
            nos.setEsquerda(rotacaoEsquerda(nos.getEsquerda()));
            return rotacaoDireita(nos);
        }
        if (balanceamento < -1 && getBalanceamento(nos.getDireita()) <= 0) {
            return rotacaoEsquerda(nos);
        }
        if (balanceamento < -1 && getBalanceamento(nos.getDireita()) > 0) {
            nos.setDireita(rotacaoDireita(nos.getDireita()));
            return rotacaoEsquerda(nos);
        }

        return nos;
    }

    private NosArvore<T> adicionarBalancearValor(NosArvore<T> nos, T valor) {
        if (nos == null) {
            tamanho++;
            return new NosArvore<T>(valor);
        }

        if (valor.compareTo(nos.getValor()) < 0) {
            nos.setEsquerda(adicionarBalancearValor(nos.getEsquerda(), valor));
        } else {
            nos.setDireita(adicionarBalancearValor(nos.getDireita(), valor));
        }

        nos.setAltura(1 + Math.max(getAltura(nos.getEsquerda()), getAltura(nos.getDireita())));

        int balanceamento = getBalanceamento(nos);

        if (balanceamento > 1 && valor.compareTo(nos.getEsquerda().getValor()) < 0) {
            return rotacaoDireita(nos);
        }
        if (balanceamento < -1 && valor.compareTo(nos.getDireita().getValor()) > 0) {
            return rotacaoEsquerda(nos);
        }
        if (balanceamento > 1 && valor.compareTo(nos.getEsquerda().getValor()) > 0) {
            nos.setEsquerda(rotacaoEsquerda(nos.getEsquerda()));
            return rotacaoDireita(nos);
        }
        if (balanceamento < -1 && valor.compareTo(nos.getDireita().getValor()) < 0) {
            nos.setDireita(rotacaoDireita(nos.getDireita()));
            return rotacaoEsquerda(nos);
        }

        return nos;
    }

    private NosArvore<T> getValorMinimo(NosArvore<T> nos) {
        if (nos == null || nos.getEsquerda() == null) {
            return nos;
        }
        return getValorMinimo(nos.getEsquerda());
    }

    private NosArvore<T> rotacaoDireita(NosArvore<T> c) {
        NosArvore<T> b = c.getEsquerda();
        NosArvore<T> a = b.getDireita();
        b.setDireita(c);
        c.setEsquerda(a);

        c.setAltura(1 + Math.max(getAltura(c.getEsquerda()), getAltura(c.getDireita())));
        b.setAltura(1 + Math.max(getAltura(b.getEsquerda()), getAltura(b.getDireita())));

        return b;
    }

    private NosArvore<T> rotacaoEsquerda(NosArvore<T> a) {
        NosArvore<T> b = a.getDireita();
        NosArvore<T> c = b.getEsquerda();
        b.setEsquerda(a);
        a.setDireita(c);

        a.setAltura(1 + Math.max(getAltura(a.getEsquerda()), getAltura(a.getDireita())));
        b.setAltura(1 + Math.max(getAltura(b.getEsquerda()), getAltura(b.getDireita())));

        return b;
    }
}
