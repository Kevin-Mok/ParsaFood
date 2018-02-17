package com.google.android.gms.samples.vision.ocrreader;

import java.util.ArrayList;
import java.util.Arrays;

public class TextParser {
    public static void main(String[] args) {
        TextParser tt = new TextParser();
        ArrayList<String> ii = new ArrayList<String>(Arrays.asList("ff", "ff"));
        ArrayList<ArrayList> done  = tt.checkAllergens(ii, "001000");

        for (ArrayList<String> sub : done){
            for (String str : sub){
                System.out.println(str);
            }
        }

    }
    public ArrayList<ArrayList<String>> allAllergens;

    public TextParser(){
        this.allAllergens = this.fillInAllergens();
    }

    public ArrayList fillInAllergens(){
        ArrayList returnList = new ArrayList();
        // milk allergens
        returnList.add(new ArrayList(Arrays.asList("milk", "butter", "buttermilk", "casein", "cheese", "cream",
                "curds", "lactose", "lactulose", "lactate", "custard", "yogurt")));
        // egg allergens
        returnList.add(new ArrayList(Arrays.asList("globulin", "egg", "eggs", "eggnog", "lysozyme", "albumin")));
        // peanut/nut allergens
        returnList.add(new ArrayList(Arrays.asList("peanut", "peanuts", "nuts", "nut", "almonds", "almonds",
                "beechnut", "beechnuts", "walnut", "walnuts", "butternut", "butternuts", "cashew", "cashews",
                "chestnut", "pecan", "pistachio")));
        // wheat allergens
        returnList.add(new ArrayList(Arrays.asList("wheat", "bread", "bulgur", "cereal", "cracker", "flour")));
        // soy allergens
        returnList.add(new ArrayList(Arrays.asList("soy", "soya", "miso", "tofu", "edamame", "flour")));
        // seafood allergens
        returnList.add(new ArrayList(Arrays.asList("anchovies", "bass", "catfish", "cod", "grouper", "haddock",
                "pike", "salmon", "snapper", "tilapia", "tuna", "trout", "fish", "crawfish", "crab", "krill",
                "lobster", "shrimp", "mussels", "squid")));
        return returnList;
    }

    public ArrayList checkAllergens(ArrayList<String> ingredients, String userAllergens){
        ArrayList returnList = new ArrayList();
        ArrayList<String> allIngredients = new ArrayList();
        ArrayList<String> userAllergensList = new ArrayList();
        ArrayList mapping = new ArrayList(Arrays.asList("milk allergen(s)", "egg allergen(s)", "peanut/nut allergen(s)",
                "wheat allergen(s)", "soy allergen(s)", "seafood allergen(s)"));


        // turn ingredient list passed in into a single array called allIngredients
        String line;
        String[] linePieces;
        for (String str : ingredients){
            line = str.toLowerCase();
            linePieces = line.split(" ");
            for (String str2 : linePieces){
                allIngredients.add(str2);
            }
        }

        // process the user allergens
        linePieces = userAllergens.split("");
        for (String str : linePieces) {
            userAllergensList.add(str);
        }

        // now iterate and find any allergens
        ArrayList temp;
        for (int index = 0; index < 6 ; index++){
            if (userAllergensList.get(index).equals("1")) {
                temp = new ArrayList();
                temp.add(("Possible " + mapping.get(index)));
                for (String allergen : this.allAllergens.get(index)){
                    for (String ingredient : allIngredients) {
                        if (allergen.equals(ingredient)) {
                            temp.add(allergen);
                        }
                    }
                }
                returnList.add(temp);
            }
        }
        return returnList;
    }

}
