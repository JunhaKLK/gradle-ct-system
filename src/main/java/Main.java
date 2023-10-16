import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static boolean isMinLevel(int index) {
        //    f(0) t f f t t t t f f f f
        return ((31 - Integer.numberOfLeadingZeros(index)) % 2) == 0;
    }

    public static void swap(ArrayList<Integer> minMaxHeap, int pos1, int pos2) {
        int temp = minMaxHeap.get(pos1);
        minMaxHeap.set(pos1, minMaxHeap.get(pos2));
        minMaxHeap.set(pos2, temp);
    }

    public static void pushDown(ArrayList<Integer> minMaxHeap, int pos) {
        if (isMinLevel(pos)) {
            if (minMaxHeap.size() < pos * 4) {
                // no grandchild
                if (minMaxHeap.size() < pos * 2) {
                    // no child
                }
                return;
            } else {
                int minPos = pos * 4;
                int max = pos * 4 + 4;
                if (pos * 4 + 4 > minMaxHeap.size()) {
                    max = minMaxHeap.size() - 1;
                }

                for (int i = pos * 4; i < max; i++) {
                    if (minMaxHeap.get(i) < minMaxHeap.get(minPos)) {
                        minPos = i;
                    }
                }

                int temp = minMaxHeap.get(pos);
                minMaxHeap.set(pos, minMaxHeap.get(minPos));
                minMaxHeap.set(minPos, temp);

                pushDown(minMaxHeap, minPos);
            }
        } else {
        }
    }

    public static void heapify(ArrayList<Integer> minMaxHeap, int pos) {
        if (pos == 1) {
            return;
        } else {
            if (isMinLevel(pos)) {
                // min level
                if (minMaxHeap.get(pos / 2) < minMaxHeap.get(pos)) {
                    swap(minMaxHeap, pos / 2, pos);

                    heapify(minMaxHeap, pos / 2);
                }

                if (minMaxHeap.get(pos / 4) > minMaxHeap.get(pos)) {
                    swap(minMaxHeap, pos / 4, pos);

                    heapify(minMaxHeap, pos / 4);
                }
            } else {
                // max level
                // only exceptions occurs here
                // check grandparent exist

                if (minMaxHeap.get(pos / 2) > minMaxHeap.get(pos)) {
                    swap(minMaxHeap, pos / 2, pos);

                    heapify(minMaxHeap, pos / 2);
                }

                if (pos < 4) {
                    // max level, no grandparents
                    if (minMaxHeap.get(1) > minMaxHeap.get(pos)) {
                        swap(minMaxHeap, 1, pos);
                    }
                } else {
                    if (minMaxHeap.get(pos / 4) < minMaxHeap.get(pos)) {
                        // pos is bigger than grandparent node
                        swap(minMaxHeap, pos / 4, pos);

                        heapify(minMaxHeap, pos / 4);
                    }
                }
            }
        }
    }

    public static void problemSolver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //min-max heap
        //parent = c//2
        ArrayList<Integer> minMaxHeap = new ArrayList<>();
        minMaxHeap.add(0);

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine());

            for (int j = 0; j < k; j++) {
                String inputString = br.readLine();
                String[] tempString = inputString.split(" ");

                String code = tempString[0];
                int data = Integer.parseInt(tempString[1]);

                if (code.equals("I")) {
                    //insert
                    minMaxHeap.add(data);

                    int addedPos = minMaxHeap.size() - 1;
                    heapify(minMaxHeap, addedPos);

                } else if (code.equals("D")) {
                    if (minMaxHeap.size() == 1) {
                        System.out.println("EMPTY");
                    } else {
                        int pop;

                        if (data == 1) {
                            //pop max
                            if (minMaxHeap.size() == 2) {
                                pop = minMaxHeap.get(1);
                                minMaxHeap.remove(1);
                            } else if (minMaxHeap.size() == 3) {
                                pop = minMaxHeap.get(2);
                                minMaxHeap.remove(2);
                            } else {
                                if (minMaxHeap.get(2) < minMaxHeap.get(3)) {
                                    pop = minMaxHeap.get(3);
                                    minMaxHeap.set(3, minMaxHeap.get(minMaxHeap.size() - 1));
                                    minMaxHeap.remove(minMaxHeap.size() - 1);
                                    pushDown(minMaxHeap, 3);
                                } else {
                                    pop = minMaxHeap.get(2);
                                    minMaxHeap.set(2, minMaxHeap.get(minMaxHeap.size() - 1));
                                    minMaxHeap.remove(minMaxHeap.size() - 1);
                                    pushDown(minMaxHeap, 2);
                                }
                            }
                        } else {
                            //pop min
                            pop = minMaxHeap.get(1);
                            minMaxHeap.set(1, minMaxHeap.get(minMaxHeap.size() - 1));
                            minMaxHeap.remove(minMaxHeap.size() - 1);

                            if (minMaxHeap.size() != 1) {
                                pushDown(minMaxHeap, 1);
                            }
                        }
                        System.out.println(pop);
                    }
                }
                System.out.println(minMaxHeap);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        problemSolver();
    }
}