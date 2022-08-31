public class LSE<T extends Comparable<T>> {

    private LSENode<T> prim;
    private LSENode<T> ult;
    private int qtd;

    LSENode<T> getPrim() {
        return prim;
    }

    int getQtd() {
        return qtd;
    }

    public void inseri(T valor) { // método de inserção ordenada
        LSENode<T> novo = new LSENode(valor);
        LSENode<T> atual, anterior = null;
        if (qtd == 0) { // inserir na lista vazia
            prim = novo;
            ult = novo;
            qtd = 1;
            System.out.println("Inserção efetuada!");
        } else if (novo.getInfo().compareTo(prim.getInfo()) < 0) { // inserir no início da lista
            novo.setProx(prim);
            prim = novo;
            qtd++;
            System.out.println("Inserção efetuada!");
        } else if (novo.getInfo().compareTo(ult.getInfo()) > 0) { // inserir no final da lista
            ult.setProx(novo);
            ult = novo;
            qtd++;
            System.out.println("Inserção efetuada!");
        } else { // inserção no meio da lista
            atual = prim;
            while (atual != null) {
                if (novo.getInfo().compareTo(atual.getInfo()) == 0) {
                    System.out.println("Valor repetido. Inserção não efetuada!");
                    return;
                } else if (novo.getInfo().compareTo(atual.getInfo()) < 0) { // inserir
                    anterior.setProx(novo);
                    novo.setProx(atual);
                    qtd++;
                    System.out.println("Inserção efetuada!");
                    return;
                } else {
                    anterior = atual;
                    atual = atual.getProx();
                }
            }
        }
    }

    //metodo de insercao ordenada permitindo repetidos
    public void inseriOrdenadoRepetido(T valor) { // método de inserção ordenada
        LSENode<T> novo = new LSENode(valor);
        LSENode<T> atual, anterior = null;
        if (qtd == 0) { // inserir na lista vazia
            prim = novo;
            ult = novo;
            qtd = 1;
            System.out.println("Inserção efetuada!");
        } else if (qtd == 1) {
            prim.setProx(novo);
            ult = novo;
            qtd++;
        } else if (novo.getInfo().compareTo(prim.getInfo()) < 0) { // inserir no início da lista
            novo.setProx(prim);
            prim = novo;
            qtd++;
            System.out.println("Inserção efetuada!");
        } else if (novo.getInfo().compareTo(ult.getInfo()) > 0) { // inserir no final da lista
            ult.setProx(novo);
            ult = novo;
            qtd++;
            System.out.println("Inserção efetuada!");
        } else { // inserção no meio da lista
            atual = prim;
            while (atual != null) {
                if (novo.getInfo().compareTo(atual.getInfo()) == 0) {
                    LSENode<T> anterior2;
                    anterior2 = anterior;
                    anterior = atual;
                    atual = atual.getProx();
                    while (atual != null && novo.getInfo().compareTo(atual.getInfo()) == 0) {
                        anterior2 = anterior;
                        anterior = atual;
                        atual = atual.getProx();
                    }
                    anterior2.setProx(novo);
                    novo.setProx(anterior);
                    qtd++;
                    return;
                } else if (novo.getInfo().compareTo(atual.getInfo()) < 0) { // inserir
                    anterior.setProx(novo);
                    novo.setProx(atual);
                    qtd++;
                    System.out.println("Inserção efetuada!");
                    return;
                } else {
                    anterior = atual;
                    atual = atual.getProx();
                }
            }
        }
    }

    //metodo de remorcao ordenada incluindo repetidos
    public void removeOrdenadoRepetido(T valor) {
        LSENode<T> result, atual, anterior;
        int cont = 0;
        result = buscaProd(valor);
        if (result == null) {
            System.err.println("Erro, Valor NÃO encontrado!");
        } else if (qtd == 1) {
            prim = null;
            ult = null;
            qtd--;
            System.out.println("Foi removido 1 valor com sucesso!");
        } else {
            atual = prim;
            anterior = prim;
            while (atual != null) {
                if (result.getInfo().compareTo(atual.getInfo()) == 0) {
                    LSENode<T> anterior2;
                    anterior2 = anterior;
                    anterior = atual;
                    atual = atual.getProx();
                    anterior2.setProx(anterior.getProx());
                    qtd--;
                    cont++;
                    while (atual != null && result.getInfo().compareTo(atual.getInfo()) == 0) {
                        anterior2 = anterior;
                        anterior = atual;
                        atual = atual.getProx();
                        anterior2.setProx(anterior.getProx());
                        qtd--;
                        cont++;
                    }
                    System.out.println("Foi removido " + cont + " valor(es) com sucesso!");
                    break;
                }
                anterior = atual;
                atual = atual.getProx();
            }
        }
    }

    public void removeRepetido(T valor) {
        LSENode<T> atual, anterior;
        int cont = 0;
        if (qtd == 0) {
            System.err.println("Erro, lista vázia!");
        } else if (qtd == 1) {
            if (valor.compareTo(prim.getInfo()) == 0) {
                prim = null;
                ult = null;
                qtd--;
                System.out.println("Foi removido 1 valor com sucesso!");
            } else {
                System.err.println("Valor NÃO encontrado!");
            }
        } else {
            atual = prim;
            anterior = prim;
            while (atual.getProx() != null) {
                if (valor.compareTo(prim.getInfo()) == 0) {
                    prim = prim.getProx();
                    atual = prim;
                    anterior = prim;
                    qtd--;
                    cont++;
                } else if (valor.compareTo(atual.getInfo()) == 0) {
                    anterior.setProx(atual.getProx());
                    atual = anterior.getProx();
                    qtd--;
                    cont++;
                } else {
                    anterior = atual;
                    atual = atual.getProx();
                }
            }
            if (atual.getInfo().compareTo(valor) == 0) {
                atual = anterior.getProx();
                qtd--;
                cont++;
                ult = anterior;
            }
            if (cont > 0) {
                System.out.println("Foi removido " + cont + " valor(es) com sucesso!");
            } else {
                System.err.println("Valor NÃO encontrado!");
            }
        }
    }

    public boolean verifica(LSE<T> lista2) {
        LSENode<T> aux, aux2;
        int qtd2 = lista2.qtd;
        if (this.qtd != qtd2) {
            return false;
        } else {
            aux = this.prim;
            aux2 = lista2.prim;
            while (aux != null) {
                if (aux.getInfo().compareTo(aux2.getInfo()) != 0) {
                    return false;
                }
                aux = aux.getProx();
                aux2 = aux2.getProx();
            }
            return true;
        }
    }

    // métodos publicos para manipulação da lista
    public void inserirValorInicio(T valor) {
        LSENode<T> novo;
        LSENode<T> aux;
        boolean achou;
        novo = new LSENode(valor);
        if (qtd == 0) { // lista está vazia
            prim = novo;
            ult = novo;
            qtd++;
        } else { // lista não está vazia
            achou = this.buscaNum(valor);
            if (achou == true) { // achou!
                System.out.println("Valor repetido! Inserção não efetuada!");
            } else {
                aux = new LSENode(valor);
                aux.setProx(prim);
                prim = aux;
                qtd++;
                System.out.println("Inserção efetuada com sucesso!");
            }
        }
    }

    public void inserirValorFinal(T valor) {
        LSENode<T> novo;
        boolean achou;
        novo = new LSENode(valor);
        if (qtd == 0) { // lista está vazia
            prim = novo;
            ult = novo;
            qtd++;
        } else { // lista não está vazia
            achou = this.buscaNum(valor);
            if (achou == true) { // achou!
                System.out.println("Valor repetido! Inserção não efetuada!");
            } else {  // não achou!!!
                ult.setProx(novo);
                ult = novo;
                qtd++;
                System.out.println("Inserção efetuada com sucesso!");
            }
        }
    }

    public void inserirValorSemV(T valor) {
        LSENode<T> novo;
        boolean achou;
        novo = new LSENode(valor);
        if (qtd == 0) { // lista está vazia
            prim = novo;
            ult = novo;
            qtd++;
        } else { // lista não está vazia
            ult.setProx(novo);
            ult = novo;
            qtd++;
            System.out.println("Inserção efetuada com sucesso!");
        }
    }

    public void RemValorInicio() {
        if (qtd == 0) { // lista está vazia
            System.err.println("Erro, lista vázia.");
        } else if (qtd == 1) {
            prim = null;
            ult = null;
            qtd--;
            System.out.println("Remoção efetuada com sucesso!");
        } else { // lista não está vazia
            prim = prim.getProx();
            qtd--;
            System.out.println("Remoção efetuada com sucesso!");
        }
    }

    public void RemValorFinal() {
        LSENode<T> aux;
        if (qtd == 0) { // lista está vazia
            System.err.println("Erro, lista vázia.");
        } else if (qtd == 1) {
            prim = null;
            ult = null;
            qtd--;
            System.out.println("Remoção efetuada com sucesso!");
        } else { // lista não está vazia
            aux = prim;
            for (int i = 1; i < qtd - 1; i++) {
                aux = aux.getProx();
            }
            ult = aux;
            aux.setProx(null);
            qtd--;
            System.out.println("Remoção efetuada com sucesso!");
        }
    }

    public boolean encontra(T valor) {
        LSENode<T> aux;
        if (qtd == 0) { // lista vazia
            return false;
        } else {
            aux = prim;
            while (aux != null) {
                if (aux.getInfo().equals(valor)) {
                    return true;
                } else {
                    aux = aux.getProx();
                }
            }
            return false;
        }
    }

    public void removeValor(T valor) {
        LSENode<T> result, auxA, auxB;
        result = buscaProd(valor);
        if (result == null) {
            System.err.println("Erro, Valor NÃO encontrado!");
        } else if (qtd == 1) {
            prim = null;
            ult = null;
            qtd--;
            System.out.println("Valor removido com sucesso!");
        } else if (result == prim) {
            prim = prim.getProx();
            qtd--;
            System.out.println("Valor removido com sucesso!");
        } else {
            auxA = prim;
            auxB = prim;
            for (int i = 1; i < qtd - 1; i++) {
                auxA = auxA.getProx();
                if (result == auxA) {
                    auxB.setProx(auxA.getProx());
                    qtd--;
                    System.out.println("Valor removido com sucesso!");
                    break;
                }
                auxB = auxA;
            }
        }
    }

    public boolean buscaNum(T valor) { // Busca sequencial simples
        LSENode<T> aux;
        if (qtd == 0) { // lista vazia
            return false;
        } else {
            aux = prim;
            while (aux != null) {
                if (aux.getInfo().compareTo(valor) == 0) {
                    return true;
                } else {
                    aux = aux.getProx();
                }
            }
            return false;
        }
    }

    private LSENode<T> buscaProd(T p) { // Busca sequencial simples
        LSENode<T> aux;
        T aux2;
        if (qtd == 0) { // lista vazia
            return null;
        } else {
            aux = prim;
            for (int i = 0; i < qtd; i++) {
                aux2 = aux.getInfo();
                if (aux2.compareTo(p) == 0) {
                    return aux;
                }
                aux = aux.getProx();
            }
            return null;
        }
    }

    public void exibirValor(T p) {
        LSENode<T> aux;
        if (qtd == 0) {
            System.out.println("Lista vazia!!");
        } else {
            aux = buscaProd(p);
            if (aux == null) {
                System.err.println("Valor não cadastrado!");
            } else {
                System.out.println(aux.getInfo());
            }
        }
    }

    public T buscarObjeto(T valor) {
        LSENode<T> pos;
        pos = buscaProd(valor);
        if (pos == null) {
            return null;
        } else {
            return pos.getInfo(); // <<<=== retornando o objeto
        }
    }

    public void exibirValores() {
        LSENode<T> aux;
        if (qtd == 0) {
            System.out.println("Lista vazia!!");
        } else {
            aux = prim;
            while (aux != null) {
                System.out.println(aux.getInfo());
                aux = aux.getProx();
            }
        }
    }
}