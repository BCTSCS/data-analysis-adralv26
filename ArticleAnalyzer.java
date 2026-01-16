import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleAnalyzer {
    private ArrayList<String> stopWords;
    private ArrayList<Article> articles;
    public ArticleAnalyzer() {
        stopWords = FileOperator.getStringList("stopwords.txt");
        System.out.println("Stop word count: "+ stopWords.size());
        articles = new ArrayList<>();
        System.out.println("Article count: "+articles.size());
    }
    public static void main(String[] args) {
        ArticleAnalyzer analyzer = new ArticleAnalyzer();
        ArrayList<String> content = FileOperator.getStringList("./1-15/data.txt");

        String line = content.get(0);
        Article a = analyzer.parseJson(line);
        
        System.out.println(a.toString());
        String clean = analyzer.removeStopWords(a.getDescription());
        a.setDescription(clean);
        analyzer.addArticle(a);
        System.out.println(a);



        // TextProcessor t = new TextProcessor(content);
        // t.removeStopWords(stopwords);
        // System.out.println(t.toString());
    }
    public Article parseJson(String jsonLine) {
        Article result;
        Pattern l = Pattern.compile("\"link\":\\s*\"([^\"]+)\"");
        Matcher lm =l.matcher(jsonLine);
        String link = lm.find() ? lm.group(1) : "";
        
        Pattern h = Pattern.compile("\"headline\":\\s*\"([^\"]+)\"");
        Matcher hm =h.matcher(jsonLine);
        String headline = hm.find() ? hm.group(1) : "";
        
        Pattern c = Pattern.compile("\"category\":\\s*\"([^\"]+)\"");
        Matcher cm =c.matcher(jsonLine); 
        String category = cm.find() ? cm.group(1) : "";
        
        Pattern d = Pattern.compile("\"short_description\":\\s*\"([^\"]+)\"");
        Matcher dm =d.matcher(jsonLine);
        String description = dm.find() ? dm.group(1) : "";
        
        Pattern a = Pattern.compile("\"authors\":\\s*\"([^\"]+)\"");
        Matcher am =a.matcher(jsonLine);
        String author = am.find() ? am.group(1) : "";
        
        Pattern da = Pattern.compile("\"date\":\\s*\"([^\"]+)\"");
        Matcher dam =da.matcher(jsonLine);
        String date = dam.find() ? dam.group(1) : "";
        
        result = new Article(link, headline, category, description, author, date);


        return result;
    }
    public void addStopWord(String word) {

    }
    public String removeStopWords(String text) {
        ArrayList<String> stopwords = FileOperator.getStringList("./stopwords.txt");

        for (String word: stopWords){
        text = text.replaceAll("\\b"+word+" \\b", "");
        }
        return text;
    }
    public void addArticle(Article article) {
        articles.add(article);
    }
}