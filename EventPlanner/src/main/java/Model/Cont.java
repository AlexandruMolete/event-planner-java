package Model;

import java.util.Objects;

public class Cont {
    private int id;
    private String nume,parola;

    public Cont() {

    }

    public static class Builder{
        private Cont cont=new Cont();
        public Builder setId(int id) {
            cont.id = id;
            return this;
        }

        public Builder setNume(String nume) {
            cont.nume = nume;
            return this;
        }

        public Builder setParola(String parola) {
            cont.parola = parola;
            return this;
        }

        public Cont build(){
            return cont;
        }
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getParola() {
        return parola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cont cont = (Cont) o;
        return id == cont.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cont{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
