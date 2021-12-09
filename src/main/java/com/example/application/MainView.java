package com.example.application;

import com.vaadin.flow.router.Route;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.lang.String;

@Route(value = "Ztest")//localhost:8080
public class MainView {
    public static ArrayList<String> NameArray = new ArrayList<>();
    public static ArrayList<String> GroupName = new ArrayList<>();
    public static ArrayList<String> PopulationData = new ArrayList<>();
    public static ArrayList<String> SampleData = new ArrayList<>();
    public static ArrayList<String> GroupData = new ArrayList<>();
    public static ArrayList<String> GroupNameArray = new ArrayList<>();
    public static NumberFormat form = new DecimalFormat("#0.000");
    public static double[] arraySGPA;
    public static double[] arrayPGPA;
    public static double[] arrayGGPA;
    public static double sampleMean; //section or group
    public static double popMean; //total popluation of data
    public static double popSD; //total population of data SD
    public static double zScore;
    public static HashMap<String,Integer> PopulationMap = new HashMap<>();
    public static HashMap<String,Integer> SampleMap = new HashMap<>();
    public static String sig = "";
    public static Integer totalP, totalS, totalG;

public MainView() throws Exception{
    readNames("./AllGroups.txt");
}
    
public MainView(String in) throws Exception{
    Integer n = Integer.parseInt(in);
    Scanner scan = new Scanner(System.in);
        compareClass(scan, n);
}

public MainView(String in, String in2) throws Exception{
    Scanner scan = new Scanner(System.in);
    Integer n = Integer.parseInt(in);
    Integer n2 = Integer.parseInt(in2);
    compareGroup(scan, n, n2);
}
        
    private static void compareGroup(Scanner scan, Integer in, Integer in2) throws Exception{
        SampleData.clear();
        GroupData.clear();
        String fName, fileName;
        readGroupFile("COMSCprogram.GRP", GroupNameArray, PopulationData);
        totalP = PopulationData.size();
        
        //comparing class to group
        fileName = GroupNameArray.get(in-1);
        readFiletoArray(fileName, SampleData);
        totalS = SampleData.size();
      
        fName = GroupName.get(in2-1);
        readGroupFile(fName, NameArray, GroupData);
        totalG = GroupData.size();
       
        NameArray.clear();
        //dispalying group data
        ztestGrp(SampleData, GroupData);
        sigfig();
    }

    private static void compareClass(Scanner scan, Integer in) throws Exception{
        SampleData.clear();
        String fileName;
        readGroupFile("COMSCprogram.GRP", GroupNameArray, PopulationData);
        totalP = PopulationData.size();
        //prompts user to select a class to compare 
        fileName = GroupNameArray.get(in - 1);
        readFiletoArray(fileName, SampleData);
        totalS = SampleData.size();
        //displaying class data
        ztestClass(SampleData);
        sigfig();
    }

    //Determines if zScore is significant
    private static void sigfig(){
        if(zScore < -2.0 || zScore > 2.0){
            sig = "Z-score is significant!";
            
        }else{
            sig = "Z-score is not significant!";
           
        }
    }
    
    //Collects group names from AllGroups.txt
    private static void readNames(String name) throws Exception{
        
        FileInputStream fstream = new FileInputStream("./classes/"+name);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;

        while ((strLine = br.readLine()) != null)   {
            GroupName.add(strLine);
        }

        br.close();
        fstream.close();
    }
    //reads in the group class to arraylist
    private static void readFiletoArray(String name, ArrayList<String> array) throws Exception{

        String[] col;
        FileInputStream fstream = new FileInputStream("./classes/"+name);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        br.readLine();
    
        while ((strLine = br.readLine()) != null)   {
    
            strLine = strLine.replace((char)9, ',');
            col = strLine.split("\\,", 5); // seperates data by colunm 
            array.add(col[col.length-1]); 
        }
    
        br.close();
        fstream.close();
    
    
    }
    //reads in call glasses from group
    private static void readGroupFile(String name, ArrayList<String> arraySz, ArrayList<String> arrayValues) throws Exception{
    
        String[] col;
        FileInputStream fstream = new FileInputStream("./classes/"+name);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        br.readLine();
    
        while ((strLine = br.readLine()) != null)   {
            arraySz.add(strLine);
        }

        br.close();
        fstream.close();

        for(int i = 0; i < arraySz.size(); i++){
            FileInputStream arrstream = new FileInputStream("./classes/"+arraySz.get(i));
            BufferedReader ar = new BufferedReader(new InputStreamReader(arrstream));
            String str;
            ar.readLine();
            

            while((str = ar.readLine()) != null){

                str = str.replace((char)9, ',');
                col = str.split("\\,", 5);
                arrayValues.add(col[col.length-1]); 
            }

        }
        
    }

        //ZTEST
        //Ztest driver
    public static void ztestGrp(ArrayList<String> arraySam, ArrayList<String> arrayGrp){
        PopulationMap.clear();
        SampleMap.clear();
   
        arrayGGPA = new double[arrayGrp.size()];
        arraySGPA = new double[arraySam.size()];

        gpa(arrayGrp, arrayGGPA, PopulationMap);
        PopulationMap = sortByValue(PopulationMap);

        gpa(arraySam, arraySGPA, SampleMap);
        SampleMap = sortByValue(SampleMap);

        populationMean(arrayGGPA);
        samplemean(arraySGPA);
        calculateSD(arrayGGPA);
        test();

        PopulationData.clear();
        GroupNameArray.clear();
    }

    public static void ztestClass(ArrayList<String> arraySam){
        PopulationMap.clear();
        SampleMap.clear();
        
        arrayPGPA = new double[PopulationData.size()];
        arraySGPA = new double[arraySam.size()];

        gpa(PopulationData, arrayPGPA, PopulationMap);
        PopulationMap = sortByValue(PopulationMap);

        gpa(arraySam, arraySGPA, SampleMap);
        SampleMap = sortByValue(SampleMap);
        
        populationMean(arrayPGPA);
        samplemean(arraySGPA);
        calculateSD(arrayPGPA);
        test();
        
        PopulationData.clear();
        GroupNameArray.clear();
    }
    
        //ztest calculation
        public static void test(){
            zScore = (popMean - sampleMean)/ popSD;
        }
        //finds sample mean
        public static void samplemean(double[] array)
        {
            double sum = 0;
            int length = array.length;
            for(double num : array){
                sum += num;
            }
            sampleMean = sum/length;
        }
        //finds population/group mean
        public static void populationMean(double[] array)
        {
            double sum = 0;
            int length = array.length;
            for(double num : array){
                sum += num;
            }
            popMean = sum/length;
        }
        //calculates standard deviation
        public static void calculateSD(double arrayNum[])
        {
            double sum = 0.0, standardDeviation = 0.0;
            int length = arrayNum.length;
    
            for(double num : arrayNum) {
                sum += num;
            }
    
            double mean = sum/length;
    
            for(double num: arrayNum) {
                standardDeviation += Math.pow(num - mean, 2);
            }
    
            popSD = Math.sqrt(standardDeviation/length);
        }
        //Sorts each HashMap by value greatest-least
        public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hashmap)
        {
            List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hashmap.entrySet());
     
            Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
                
                public int compare(Map.Entry<String, Integer> v1,
                                   Map.Entry<String, Integer> v2)
                {
                    return (v2.getValue()).compareTo(v1.getValue());
                }
            });
    
            HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
            
            for (Map.Entry<String, Integer> val : list) {
                temp.put(val.getKey(), val.getValue());
            }
            return temp;
        }
        //Sets maps with values and keys
        //Sets arrays with numerical grade equivalents
        public static void gpa(ArrayList<String> arrayl, double[] arraylVal, HashMap<String, Integer> map){
            int value = 0;
            for(int i = 0; i < arrayl.size(); i++){
                String grade = arrayl.get(i).toString();
                if(!map.containsKey(grade)){
                    map.put(grade, 0);
                }
                switch(grade)
                {
                    case "A" :
                        arraylVal[i] = 4;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "A-" :
                        arraylVal[i] = 3.66;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "B+" :
                        arraylVal[i] = 3.33;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "B" :
                        arraylVal[i] = 3;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "B-" :
                        arraylVal[i] = 2.66;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "C+" :
                        arraylVal[i] = 2.33;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "C" :
                        arraylVal[i] = 2;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "C-" :
                        arraylVal[i] = 1.66;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "D+" :
                        arraylVal[i] = 1.33;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "D" :
                        arraylVal[i] = 1;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "D-" :
                        arraylVal[i] = .66;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
    
                    case "F" :
                        arraylVal[i] = 0;
                        value = map.get(grade);
                        value += 1;
                        map.replace(grade, value);
                    break;
                }      
            }
        }
    }
