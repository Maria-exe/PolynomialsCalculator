package datamodels;

public abstract class Monomial implements Comparable <Monomial> {
    private Integer degree;
    private boolean processed;

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed() {
        this.processed = true;
    }

    @Override
    public int compareTo(Monomial m) {
        return this.getDegree().compareTo(m.getDegree());
    }
}


