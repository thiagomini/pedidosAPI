package ufes.com.br.SimpleAPI.model;

public abstract class AbstractModel implements Comparable {
    protected long id;

    @Override
    public boolean equals(Object obj) {
        if ((obj.getClass() != this.getClass()) ) {
            return false;
        }

        AbstractModel castedObject = (AbstractModel) obj;

        return castedObject.getId() == this.getId();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        AbstractModel convertedObject = (AbstractModel) o;
        return (int) (this.getId() - convertedObject.getId());
    }
}
