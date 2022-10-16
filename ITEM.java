package Paket16_GuıBitirme.Helper;

public class ITEM {
    private int key;
    private String value;

    public ITEM(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toString(){
        return this.value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
