import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewCollector {
    private ArrayList<ProductReview> reviewList;
    private ArrayList<String> productList;
    public ReviewCollector(){
        reviewList = new ArrayList<>();
        productList = new ArrayList<>();
    }
    public void addReview(ProductReview prodReview){
        reviewList.add(prodReview);
        String prodName = prodReview.getName();
        for (String product : productList) {
            if (!(product == prodName)){
                productList.add(prodName);
            }

        }
        System.out.println("Current review list size: " + reviewList.size());
    }

    public double getSentiments(String wordSearch) {
        // Read lines from sentiments.txt
        ArrayList<String> lines = FileOperator.getStringList("./1-22/sentiments.txt");
 
 
        // Regex pattern to match word,decimal pairs
        Pattern pattern = Pattern.compile("([a-zA-Z0-9]+),(-?\\d+\\.\\d+)");

        // Process each line
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String word = matcher.group(1); // Extract the word
                Double value = Double.parseDouble(matcher.group(2)); // Extract the value
 
 
            
                                if(wordSearch.equals(word)){
   // Print the result
                // System.out.println(word + "   ----  " + value);
 
 
                    return value;
                }
  
            }
          
        }
         return 0.0;
    }
 
 
   



    public int getNumGoodReviews(String prodName){
        int goodCount = 0;
        for (ProductReview review : reviewList){
            if (review.getName().equals(prodName)) {
                String newReview = review.getReview();
                String[] words = newReview.split(" ");
                double total = 0.0;
                for (String word : words) {
                    total += getSentiments(word);
                }
                if (total > 1) {
                    goodCount+=1;
                }
            }
        }
        return goodCount;
    }
    public static void main(String[] args){

        ReviewCollector reviewCollector= new ReviewCollector();
        ArrayList<String> lines =FileOperator.getStringList("product.txt");
        Pattern productPattern = Pattern.compile("Product:\\s*(.+)");
        Pattern reviewPattern = Pattern.compile("Review:\\s*(.+)");
        
        String productName = null;
        String review =null;
        for (String line : lines) {
        Matcher productMatcher = productPattern.matcher(line);
        Matcher reviewMatcher = reviewPattern.matcher(line);
        
        if (productMatcher.find()) {
        productName = productMatcher.group(1);
        } else if (reviewMatcher.find()) {
        review= reviewMatcher.group(1);
        
        }
        if(productName!=null && review!=null ){
        ProductReview product=new ProductReview(productName,review);
        reviewCollector.addReview(product);
        System.out.println("Amount of good reviews: " + reviewCollector.getNumGoodReviews(productName));
        System.out.println("new product memory location: " + product);
        
        productName = null;
        review =null;
        }
        }
    }
}