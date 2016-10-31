import java.util.HashMap;


/**
*   Insert your own wardrobe into this Wardrobe class and submit content to us!
*   Based on the weather, think about which of your clothes you'd wear during 
*   that temperature or condition.
*/
/**
 * Specifications:
 * There are commented regions below to specify what clothing type to 
 * enter into the ArrayLists initialized. Look at the examples in each
 * region to get an idea of what to do. The examples only adds one item
 * into each temperature range but you can add as many items as you want.
 * 
 * Specify your gender before you start adding items into the ArrayLists.
 * 
 * DONT ADD EXTRA CODE BESIDES THE CLOTHING REGIONS SPECIFIED IN COMMENTED 
 * SECTIONS DOWN BELOW. ONLY ADD ITEMS INTO ARRAYLIST, EVERYTHING ELSE IS ALREADY
 * BEING HANDLED.
 * 
 */
public class Wardrobe{

    private HashMap<Integer,ArrayList<String>> upperBody = new HashMap<>();
    private HashMap<Integer,ArrayList<String>> lowerBody = new HashMap<>();
    private HashMap<Integer,ArrayList<String>> overalls = new HashMap<>();
    private HashMap<Integer,ArrayList<String>> shoes = new HashMap<>();
    private HashMap<Integer,ArrayList<String>> miscellaneous = new HashMap<>();
    ArrayList<String> freezing;
    ArrayList<String> chilly;
    ArrayList<String> warm;
    ArrayList<String> hot; 
    String gender;

    
    public Wardrobe(){
        // Please specify your gender before you begin this template
        // Add one of the following lines:
        //     gender = "male";		OR		gender = "female";
    	

        freezing = new ArrayList<>();
        chilly = new ArrayList<>();
        warm = new ArrayList<>();
        hot = new ArrayList<>();
        
        /**
         * Upperbody region
         *	Example:
         *		freezing.add("Winter Coat");
         *		chilly.add("North Face Jacket");
         *		warm.add("Polo");
         *		hot.add("T-Shirt");
         */
        
        //Begin inserting upperbody code lines here
        
        
        
        //End UpperBody region
        
        upperBody.put(1,freezing);
        upperBody.put(2,chilly);
        upperBody.put(3,warm);
        upperBody.put(4,hot);

        freezing = new ArrayList<>();
        chilly = new ArrayList<>();
        warm = new ArrayList<>();
        hot = new ArrayList<>();
        
        /**
         * LowerBody region
         *	Example:
         *		freezing.add("Snow Pants");
         *		chilly.add("Sweatpants");
         *		warm.add("Jeans");
         *		hot.add("Shorts");
         */
        
        //Begin inserting lowerbody code lines here
        
        
        
        //End LowerBody region
        
        lowerBody.put(1, freezing);
        lowerBody.put(2, chilly);
        lowerBody.put(3, warm);
        lowerBody.put(4, hot);

        freezing = new ArrayList<>();
        chilly = new ArrayList<>();
        warm = new ArrayList<>();
        hot = new ArrayList<>();
        
        /**
         * Overalls region
         *	Example:
         *		freezing.add("Snow Overalls");
         *		warm.add("Romper");
         *		hot.add("Dress");
         */
        
        //Begin inserting overalls code lines here
        
        
        
        //End Overalls region
        
        overalls.put(1, freezing);
        overalls.put(2, chilly);
        overalls.put(3, warm);
        overalls.put(4, hot);
        
        freezing = new ArrayList<>();
        chilly = new ArrayList<>();
        warm = new ArrayList<>();
        hot = new ArrayList<>();
        
        /**
         * Shoes region
         *	Example:
         *		freezing.add("Timberland Boots");
         *		chilly.add("Sneakers");
         *		warm.add("Vans");
         *		hot.add("Sandals");
         */
        
        //Begin inserting shoes code lines here
        
        
        
        //End Shoes region
        
        shoes.put(1, freezing);
        shoes.put(2, chilly);
        shoes.put(3, warm);
        shoes.put(4, hot);
        
        freezing = new ArrayList<>();
        chilly = new ArrayList<>();
        warm = new ArrayList<>();
        hot = new ArrayList<>();
        
        /**
         * Miscellaneous region
         *	Example:
         *		freezing.add("Scarf");
         *		chilly.add("Beanie");
         *		warm.add("Sunglasses");
         *		hot.add("Sunscreen");
         */
        
        //Begin inserting miscellaneous code lines here
        
        
        
        //End Miscellaneous region
        
        miscellaneous.put(1, freezing);
        miscellaneous.put(2, chilly);
        miscellaneous.put(3, warm);
        miscellaneous.put(4, hot);
        
        /**
         * Your content is very important to us and helps us
         * improve our application. Thank you for your help!!!
         * 
         *  --
         *  The Wear Where Team
         */
    }
}
