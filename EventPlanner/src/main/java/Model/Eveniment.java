package Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Eveniment {
    private int id;
    private String nume;
    private LocalDateTime data;
    private int contId;

    public Eveniment() {

    }

    public static class Builder{
        private Eveniment eveniment=new Eveniment();

        public Builder setId(int id) {
            eveniment.id = id;
            return this;
        }

        public Builder setNume(String nume) {
            eveniment.nume = nume;
            return this;
        }

        public Builder setData(LocalDateTime data) {
            eveniment.data = data;
            return this;
        }

        public Builder setContId(int contId) {
            eveniment.contId = contId;
            return this;
        }

        public Eveniment build(){
            return eveniment;
        }
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public LocalDateTime getData() {
        return data;
    }

    public int getContId() {
        return contId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eveniment eveniment = (Eveniment) o;
        return id == eveniment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.nume+"  "+this.data;
    }
}
