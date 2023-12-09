public class HashThat {
    String[] table;
    public HashThat(int maxSize, String[] names) {
        table = new String[maxSize];
        hash(names, maxSize);
    }
    private int getLetVal(char ch) {
        return Math.abs(ch - 'a') % 26;
    }
    private int hashFunction(String name, int tableSize) {
        int hashVal = 0;
        for (int i = 0; i < Math.min(3, name.length()); i++) {
            hashVal = (hashVal * 26 + getLetVal(name.charAt(i))) % tableSize;
        }
        return Math.abs(hashVal);
    }
    private int doubleHashFunction(String name, int tableSize) {
        int hashVal = 0;
        for (int i = 0; i < Math.min(3, name.length()); i++) {
            hashVal = (hashVal * 31 + getLetVal(name.charAt(i))) % tableSize;
        }
        return Math.abs(hashVal);
    }
    private int hash(String name, int tableSize, int attempt) {
        int initialHash = hashFunction(name, tableSize);
        int doubleHash = doubleHashFunction(name, tableSize);
        int linearStep = doubleHash == 0 ? 1 : doubleHash;
        return Math.floorMod(initialHash + attempt * linearStep, tableSize);
    }

    public void hash(String[] names, int tableSize) {
        int clashes = 0;
        for (String name : names) {
            if (name != null) {
                int key = hash(name, tableSize, 0);
                int attempt = 1;

                while (table[key] != null) {
                    clashes++;
                    key = hash(name, tableSize, attempt * attempt);
                    attempt++;
                }
                table[key] = name;
            }
        }

        System.out.println("The number of collisions recorded is " + clashes);
    }

    public void reHash(int maxSize, String[] names) {
        table = new String[maxSize];
        hash(names, maxSize);
    }
    public void showTable() {
        System.out.println("HashTable: size " + table.length);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                String text = i + " : " + table[i];
                System.out.println(text);
            }
        }
        System.out.println();
    }
}
