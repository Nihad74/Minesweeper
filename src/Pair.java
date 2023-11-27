public class Pair <T>{
    private final T first;
    private final T second;

    public Pair(T first, T second){
        this.first = first;
        this.second = second;
    }

    public boolean equals(Object obj){
        if(obj instanceof Pair){
            Pair<T> pair = (Pair<T>) obj;
            return first.equals(pair.first) && second.equals(pair.second);
        }
        return false;
    }

    public T getFirst() {
        return first;
    }
    public T getSecond() {
        return second;
    }
}
