package src.main.java.Model;


public interface Observado {

    void addObservador(Observador o);
    void notificar(Observador o);
    void notificarTodos();
}
