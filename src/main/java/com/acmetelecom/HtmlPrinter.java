package com.acmetelecom;

class HtmlPrinter implements Printer {

    private static Printer instance = new HtmlPrinter();
    private String print;
    
    private HtmlPrinter() {
    	print = "";
    }

    public static Printer getInstance() {
        return instance;
    }

    public void createHeading(String name, String phoneNumber, String pricePlan) {
        beginHtml();
        print += h2(name + "/" + phoneNumber + " - " + "Price Plan: " + pricePlan) + "\n";
        beginTable();
    }

    private void beginTable() {
        print += "<table border=\"1\">\n";
        print += tr(th("Time") + th("Number") + th("Duration") + th("Cost")) + "\n";
    }

    private void endTable() {
        print += "</table>\n";
    }

    private String h2(String text) {
        return "<h2>" + text + "</h2>";
    }

    public void createItem(String time, String callee, String duration, String cost) {
        print += tr(td(time) + td(callee) + td(duration) + td(cost)) + "\n";
    }

    private String tr(String text) {
        return "<tr>" + text + "</tr>";
    }

    private String th(String text) {
        return "<th width=\"160\">" + text + "</th>";
    }

    private String td(String text) {
        return "<td>" + text + "</td>";
    }

    public void createTotal(String total) {
        endTable();
        print += h2("Total: " + total) + "\n";
        endHtml();
    }

    private void beginHtml() {
        print += "<html>\n" +
        		 "<head></head>\n" +
        		 "<body>\n" +
        		 "<h1>\n" +
        		 "Acme Telecom\n" +
        		 "</h1>\n";
    }

    private void endHtml() {
        print += "</body>\n</html>\n";
    }
    
    public String getString(){
    	return print;
    }
    
    public void printAllToConsole() {
    	System.out.println(print);
    }
}
