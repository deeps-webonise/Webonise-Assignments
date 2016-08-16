
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVReader {


    public ArrayList<restraunt> createDatabase()
    {
        ArrayList<restraunt> restraunts = new ArrayList<restraunt>();
        String csvFile ="/home/webonise/sample_data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            int temp=0;
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator

                String[] items = line.split(cvsSplitBy);
                if(Integer.parseInt(items[0]) != temp){
                    restraunts.add(new restraunt());
                    temp = Integer.parseInt(items[0]);
                }

                restraunt tempres = restraunts.get(temp-1);
                tempres.rest_id = temp;
                tempres.price.add(Float.parseFloat(items[1].trim()));
                tempres.item.add(items[2].trim());
                int i = 3;
                if(items.length > 3){
                    tempres.price.add(Float.parseFloat(items[1].trim()));
                    tempres.item.add(items[i].trim());
                    i++;
                }
                restraunts.set(temp-1,tempres);
                //r1.rest_id=Integer.parseInt(items[0].trim());
            }
//            for(int i=0;i<restraunts.size();i++){
//                System.out.println(restraunts.get(i).rest_id);
//                System.out.println(restraunts.get(i).item.toString());
//                System.out.println(restraunts.get(i).price.toString());
//
//            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return restraunts;
    }

    public static void main(String[] args) {
        System.out.println("The arguments fed are " + args[0] + " " +  args[1]);
        CSVReader csvReader=new CSVReader();
        ArrayList<restraunt> restraunts = csvReader.createDatabase();
//        System.out.println(restraunts.size());
        int restIndex = 0,restId = 0,argsLimit;
        double lastResult = 99999.0;
        while(restIndex < restraunts.size()){
            restraunt tempRest = restraunts.get(restIndex);
            float tempResult = 0;
            argsLimit = 0;
            for(int i=0;i<tempRest.item.size();i++){
                for(int argIndex = 0;argIndex < args.length;argIndex++){
                    if(args[argIndex].equalsIgnoreCase(tempRest.item.get(i))){
                        tempResult += tempRest.price.get(i);
                        argsLimit++;
                    }
                }
            }
            if(tempResult>0 && tempResult < lastResult && argsLimit == args.length){
                lastResult = tempResult;
                restId = tempRest.rest_id;
            }
            restIndex++;
        }
        if(lastResult < 99999) {
            System.out.println(restId + " " + lastResult);
        }else{
            System.out.println(false);
        }
    }

}


