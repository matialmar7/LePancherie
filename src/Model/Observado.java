package src.Model;


public interface Observado {

    public void addObservador(Observador o);
    public void notificar(Observador o);
    public void notificarTodos();
}
