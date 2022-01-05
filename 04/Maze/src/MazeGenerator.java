import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class MazeGenerator {
    public static class randomMaze {
        private static class Cell {
            private char status = '0';

            boolean isOpen() {
                return status == '0';
            }

            boolean isWall() {
                return status == '1';
            }

            void setWall() {
                status = '1';
            }

            void setGround() {
                status = '*';
            }

            void setEntry() {
                status = 'e';
            }

            public String toString() {
                return Character.toString(status);
            }
        }

        private enum Direction {
            Up, Right, Down, Left
        }

        Cell[][] layout;
        int rowSize;
        int columnSize;
        Random random;

        randomMaze(int m, int n) {
            rowSize = 2 * m + 1;
            columnSize = 2 * n + 1;
            random = new Random(System.currentTimeMillis());
            initializeEmptyMaze();
            createRandomMaze(m, n);
        }

        private void initializeEmptyMaze() {
            layout = new Cell[rowSize][columnSize];
            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < columnSize; j++) {
                    layout[i][j] = new Cell();
                }
            }
            for (int i = 0; i < rowSize; i++) {
                layout[i][0].setWall();
                layout[i][columnSize - 1].setWall();
            }
            for (int i = 0; i < columnSize; i++) {
                layout[0][i].setWall();
                layout[rowSize - 1][i].setWall();
            }
            for (int i = 1; i < rowSize; i += 2) {
                for (int j = 1; j < columnSize; j += 2) {
                    layout[i][j].setGround();
                }
            }
            layout[0][1].setEntry();
            layout[rowSize - 1][columnSize - 2].setEntry();
        }

        public String toString() {
            StringBuilder mazeRepresentation = new StringBuilder();
            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < columnSize; j++) {
                    mazeRepresentation.append(layout[i][j]);
                }
                if (i < rowSize - 1) {
                    mazeRepresentation.append("\n");
                }
            }
            return mazeRepresentation.toString();
        }

        private boolean isInsideWalls(int i, int j) {
            return i > 0 && i < rowSize - 1 && j > 0 && j < columnSize - 1;
        }

        private boolean isUpOkay(int i, int j) {
            if (layout[i - 1][j].isOpen()) {
                boolean badConditions = layout[i - 1][j - 1].isWall() || layout[i - 1][j + 1].isWall() || layout[i - 2][j].isWall() || layout[i - 2][j - 1].isWall() || layout[i - 2][j + 1].isWall();
                return !badConditions;
            }
            return false;
        }

        private boolean isRightOkay(int i, int j) {
            if (layout[i][j + 1].isOpen()) {
                boolean badConditions = layout[i][j + 2].isWall() || layout[i + 1][j + 1].isWall() || layout[i - 1][j + 1].isWall() || layout[i + 1][j + 2].isWall() || layout[i - 1][j + 2].isWall();
                return !badConditions;
            }
            return false;
        }

        private boolean isDownOkay(int i, int j) {
            if (layout[i + 1][j].isOpen()) {
                boolean badConditions = layout[i + 1][j - 1].isWall() || layout[i + 1][j + 1].isWall() || layout[i + 2][j].isWall() || layout[i + 2][j + 1].isWall() || layout[i + 2][j - 1].isWall();
                return !badConditions;
            }
            return false;
        }

        private boolean isLeftOkay(int i, int j) {
            if (layout[i][j - 1].isOpen()) {
                boolean badConditions = layout[i][j - 2].isWall() || layout[i + 1][j - 1].isWall() || layout[i - 1][j - 1].isWall() || layout[i - 1][j - 2].isWall() || layout[i + 1][j - 2].isWall();
                return !badConditions;
            }
            return false;
        }

        private ArrayList<Direction> getValidDirections(int i, int j) {
            ArrayList<Direction> validDirections = new ArrayList<>();
            if (isInsideWalls(i - 1, j) && isUpOkay(i, j)) {
                validDirections.add(Direction.Up);
            }
            if (isInsideWalls(i, j + 1) && isRightOkay(i, j)) {
                validDirections.add(Direction.Right);
            }
            if (isInsideWalls(i + 1, j) && isDownOkay(i, j)) {
                validDirections.add(Direction.Down);
            }
            if (isInsideWalls(i, j - 1) && isLeftOkay(i, j)) {
                validDirections.add(Direction.Left);
            }
            return validDirections;
        }

        private void createRandomMaze(int m, int n) {
            int startSide = random.nextInt(4);
            int origin;
            switch (startSide) {
                case 0 -> {
                    origin = (random.nextInt(m - 1) + 1) * 2;
                    randomMazeHelper(origin, 1);
                }
                case 1 -> {
                    origin = (random.nextInt(m - 1) + 1) * 2;
                    randomMazeHelper(origin, columnSize - 2);
                }
                case 2 -> {
                    origin = (random.nextInt(n - 1) + 1) * 2;
                    randomMazeHelper(1, origin);
                }
                case 3 -> {
                    origin = (random.nextInt(n - 1) + 1) * 2;
                    randomMazeHelper(rowSize - 2, origin);
                }
            }
        }

        private void randomMazeHelper(int i, int j) {
            layout[i][j].setWall();
            ArrayList<Direction> validDirections = getValidDirections(i, j);
            while (!validDirections.isEmpty()) {
                Direction direction = validDirections.get(random.nextInt(validDirections.size()));
                switch (direction) {
                    case Up -> randomMazeHelper(i - 1, j);
                    case Right -> randomMazeHelper(i, j + 1);
                    case Down -> randomMazeHelper(i + 1, j);
                    case Left -> randomMazeHelper(i, j - 1);
                }
                validDirections = getValidDirections(i, j);
            }
        }
    }

    public static void main(String[] args) {
        int m, n, t;
        Set<String> mazeSet = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        t = scanner.nextInt();
        randomMaze maze;
        while (mazeSet.size() != t) {
            maze = new randomMaze(m, n);
            mazeSet.add(maze.toString());
        }
        for (String mazeString : mazeSet) {
            System.out.println(mazeString + "\n");
        }
    }
}
