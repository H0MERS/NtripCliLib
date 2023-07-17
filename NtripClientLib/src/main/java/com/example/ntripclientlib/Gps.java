package com.example.ntripclientlib;

import android.text.TextUtils;

public class Gps {

    public Double Latitude = 0.00;
    public Double Longitude = 0.00;
    public String Status = "NA";

    private double ParseToDecimal(String data, String direction){
        double latLon = Double.parseDouble(data) / 100d;
        double latLonMins = latLon % 1d;
        latLon = latLon - latLonMins;
        latLonMins = latLonMins * 100d / 60d;
        double result = latLon + latLonMins;
        if((direction.equalsIgnoreCase("w")) || (direction.equalsIgnoreCase("s")))
            result = -1 * result;
        return result;
    }
    public void ProcessData(String code)
    {
        if(!code.contains("RMC")) return;

        // $GPRMC,203522.00,A,5109.0262308,N,11401.8407342,W,0.004,133.4,130522,0.0,E,D*2B
        // $GNRMC,204520.00,A,5109.0262239,N,11401.8407338,W,0.004,102.3,130522,0.0,E,D*3B
        // 0       1        2       3      4      5        6   7    8       9   20  11  12
        String[] d = code.split(",");
        if(d.length > 11){

            //Parse Lat/Lon
            if((!TextUtils.isEmpty(d[3]) && (!TextUtils.isEmpty(d[5])))){
                Latitude = ParseToDecimal(d[3],d[4]);
                Longitude = ParseToDecimal(d[5],d[6]);
            }
            String quality ="";
            try {
                quality = String.valueOf(d[12].charAt(0));
            }
            catch (Exception e){
                e.printStackTrace();
            }
            switch (quality){
                case "A": Status = "GPS";
                    break;
                case "D": Status = "DGPS";
                    break;
                case "P": Status = "PPS";
                    break;
                case "R": Status = "RTK";
                    break;
                case "F": Status = "fRTK";
                    break;
                case "E": Status = "Estimated";
                    break;
                case "M": Status = "Manual";
                    break;
                case "S": Status = "Simulation";
                    break;
                case "W": Status = "WAAS";
                    break;
                default: Status = "Invalid";
                    break;
            }




        }
    }

}
