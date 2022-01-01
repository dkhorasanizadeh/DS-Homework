public class Manager {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(5);
        Queue<Integer> queue = new Queue<>(5);
        for(int i=0;i<5;i++){
            stack.push(i);
            System.out.println("Pushed element: "+i+" current number of items: "+stack.numberOfItemsInside());
        }
        System.out.println("Search for 2 in stack: "+stack.search(2));
        System.out.println("Search for 25 in stack: "+stack.search(25));
        for(int i=0;i<5;i++){
            System.out.println("Popped element is: "+ stack.pop());
        }
        for(int i=0;i<4;i++){
            queue.enqueue(i);
            System.out.println("Enqueued element: "+i+" current number of items: "+queue.numberOfItemsInside());
        }
        System.out.println("Search for 2 in queue: "+queue.search(2));
        System.out.println("Search for 25 in queue: "+queue.search(25));
        for(int i=0;i<4;i++){
            System.out.println("Dequeued element is: "+ queue.dequeue());
        }

    }
}
