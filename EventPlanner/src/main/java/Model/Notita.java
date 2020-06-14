package Model;

import java.util.Objects;

public class Notita {
    private int id;
    private String continut;
    private int evenimentId;

    public Notita(){

    }

    public static class Builder{
        private Notita notita=new Notita();

        public Builder setId(int id) {
            notita.id = id;
            return this;
        }

        public Builder setContinut(String continut) {
            notita.continut = continut;
            return this;
        }

        public Builder setEvenimentId(int evenimentId) {
            notita.evenimentId = evenimentId;
            return this;
        }

        public Notita build(){
            return notita;
        }
    }

    public int getId() {
        return id;
    }

    public String getContinut() {
        return continut;
    }

    public int getEvenimentId() {
        return evenimentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notita notita = (Notita) o;
        return id == notita.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.continut;
    }
}
