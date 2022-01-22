import java.io.IOException;
import java.time.Instant;
import java.util.Scanner;
import java.util.Random;

public class Blockchain {
    private static class Node {
        private int index;
        private int nonce;
        private int previousHash;
        private long timeStamp;
        private Node previous;

        Node(int index, int nonce, Node previous) {
            this.setIndex(index);
            this.setNonce(nonce);
            this.setPrevious(previous);
            this.setPreviousHash(Blockchain.hash(previous));
            this.setTimeStamp(Instant.now().getEpochSecond());
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getNonce() {
            return nonce;
        }

        public void setNonce(int nonce) {
            this.nonce = nonce;
        }

        public int getPreviousHash() {
            return previousHash;
        }

        public void setPreviousHash(int previousHash) {
            this.previousHash = previousHash;
        }

        public long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
        }

        @Override
        public String toString() {
            return "--------------------------\n" +
                    "Block:" +
                    "\nIndex= " + index +
                    "\nNonce= " + nonce +
                    "\nPrevious Hash= " + previousHash +
                    "\nTime Stamp= " + timeStamp +
                    "\n--------------------------\n";
        }
    }

    Node tail;

    public static int hash(long data) {
        return (int) (data * Instant.now().getEpochSecond() % 2147483647) & Integer.MAX_VALUE;
    }

    public static int hash(Node data) {
        if (data == null) {
            return 0;
        }
        int index = data.getIndex();
        long timeStamp = data.getTimeStamp();
        int nonce = data.getNonce();
        int previousHash = data.getPreviousHash();
        return hash(index + timeStamp + nonce + previousHash);
    }

    Blockchain() {
        createBlock();
    }

    private int proofOfWork(int previousNonce) {
        Random random = new Random();
        int nonce = random.nextInt() & Integer.MAX_VALUE;
        while (!checkNonce(hash((long) nonce * nonce - (long) previousNonce * previousNonce))) {
            nonce = random.nextInt() & Integer.MAX_VALUE;
        }
        return nonce;
    }

    private boolean checkNonce(int nonce) {
        return nonce <= 483647;
    }

    private Node createBlock() {
        if (tail == null) {
            tail = new Node(0, 1, null);
        } else {
            int nonce = proofOfWork(tail.getNonce());
            int index = tail.getIndex() + 1;
            tail = new Node(index, nonce, tail);
        }
        return tail;
    }

    public void mineNewBlock() {
        Node newNode = createBlock();
        System.out.println(newNode);
    }

    public void showChain() {
        Node cursor = tail;
        while (cursor != null) {
            System.out.println(cursor);
            cursor = cursor.getPrevious();
        }
    }

    public void showBlock(int index) throws IndexOutOfBoundsException {
        if (index > tail.getIndex() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node cursor = tail;
        while (cursor.getIndex() != index) {
            cursor = cursor.getPrevious();
        }
        System.out.println(cursor);
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    private static void menu(Blockchain blockchain) {
        int choice = 0;
        Scanner input = new Scanner(System.in);
        String menuString = """
                Please select one of the following:
                1. Mine new block
                2. Show Chain
                3. Show block at index
                4. Exit""";
        while (choice != 4) {
            clearConsole();
            System.out.println(menuString);
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    blockchain.mineNewBlock();
                    break;
                case 2:
                    blockchain.showChain();
                    break;
                case 3:
                    int block;
                    System.out.println("Please enter block index: ");
                    block = input.nextInt();
                    blockchain.showBlock(block);
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        menu(blockchain);
    }
}
