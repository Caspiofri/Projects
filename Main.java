package question2;

public class Main {

	public static void main(String[] args) {
		Warmest<Integer,String> warmest = new Warmest<>();
		
		warmest.put(1,"sun");
		String ans = warmest.getWarmest();
		System.out.println(ans);
		warmest.printList();
		
		warmest.put(22,"flower");
		ans = warmest.getWarmest();
		System.out.println(ans);
		warmest.printList();
		
		warmest.put(53,"sea");
		ans = warmest.getWarmest();
		System.out.println(ans);
		warmest.printList();
			
		warmest.get(22); 
		ans = warmest.getWarmest();
		System.out.println(ans);
		warmest.printList();
		

		warmest.get(53); 
		ans = warmest.getWarmest();
		System.out.println(ans);
		warmest.printList();
		
		warmest.get(22); 
		ans = warmest.getWarmest();
		System.out.println(ans);
		warmest.printList();
		
		warmest.remove(22);
		System.out.println(ans);
		warmest.printList();
		
		ans = warmest.getWarmest();
		System.out.println(ans);
		warmest.printList();
		
		ans = warmest.remove(53);
		System.out.println(ans);
		warmest.printList();
		
		ans = warmest.getWarmest();
		System.out.println(ans);
		warmest.printList();
		ans = warmest.get(1);
		System.out.println(ans);
		warmest.printList();
		
		ans = warmest.remove(1);
		System.out.println(ans);
		warmest.printList();
		
		ans = warmest.getWarmest();
		System.out.println(ans);
		warmest.printList();
		
		/*
		Warmest<Integer,String> warmest = new Warmest<>();
		warmest.put(1,"hello");
		String ans = warmest.remove(2); 
		System.out.println(ans);
		

		Warmest<Integer,String> warmest = new Warmest<>();
		String ans = warmest.getWarmest(); 
		System.out.println(ans);
		ans =  warmest.remove(1); 
		System.out.println(ans);
		*/
	}
}

