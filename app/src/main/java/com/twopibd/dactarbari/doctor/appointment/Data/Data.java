package com.twopibd.dactarbari.doctor.appointment.Data;





import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberModel;
import com.twopibd.dactarbari.doctor.appointment.Model.CountryModel;
import com.twopibd.dactarbari.doctor.appointment.Model.Day;
import com.twopibd.dactarbari.doctor.appointment.Model.DepartmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.DoctorModel;
import com.twopibd.dactarbari.doctor.appointment.Model.HospitalModel;
import com.twopibd.dactarbari.doctor.appointment.Model.RecomentationModel;
import com.twopibd.dactarbari.doctor.appointment.Model.SpacialistModel;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by mkl on 2/14/2019.
 */

public class Data {
    public static List<Day> newSchedulelist=new ArrayList<>();
    public static SessionManager sessionManager;
    public  static String TOKEN,USER_ID;
    public static String getAddress(Context context, LatLng latLng) {
       Double lat=latLng.latitude;
       Double lng=latLng.longitude;
        String address="";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
           /* add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();
            */
            Toast.makeText(context, obj.getPostalCode(), Toast.LENGTH_LONG).show();
            address=add;
            Log.v("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            address=e.getLocalizedMessage();
            e.printStackTrace();
        }
        return address;
    }
    public static String TYPE_DOCTOR="d";
    public static String TYPE_PATIENT="p";
    public static List<SpacialistModel> spacialist = new ArrayList<>();
    public static List<DoctorModel> searchResult = new ArrayList<>();
    public static List<DepartmentModel> cachedDeparmentsList = new ArrayList<>();
    public static Map<String,HospitalModel> hospitalNameBodyMap=new HashMap<>();
    public static List<HospitalModel> cachedHospitalsList = new ArrayList<>();
    public static List<CountryModel> cachedCountryList = new ArrayList<>();
    public static DoctorModel singleDrModel;
    public static RecomentationModel testList ;
    public static AppointmentModel appointmentModel ;
    public static ChamberModel chamberModel ;

    public static List<Day> days = new ArrayList<>();
    public static String USER_NAME;
    public static String TEMP_LINK;

    public static String BaseUrl="";
    public static String PHOTO_BASE="";
    public static String FACEBOOK_LINK="";
    public static Map<Integer,String>hospital_id_name_map=new HashMap<Integer,String>();
    public static Map<Integer,HospitalModel>hospital_id_Body_map=new HashMap<Integer,HospitalModel>();
    public static Map<Integer,String>department_id_name_map=new HashMap<Integer,String>();
    public static Map<Integer,String>country_id_name_map=new HashMap<Integer,String>();


    public  static  String getHospitalNameByID(int id){

        return hospital_id_name_map.get(id);
    }
    public  static  String getDepartmentNameByID(int id){

        return department_id_name_map.get(id);
    }
    public static void putHospitalMap(int key,String value){
        hospital_id_name_map.put(key,value);

    }

    public static void putDepartmentlMap(int key,String value){
        department_id_name_map.put(key,value);

    }

    public  static List<String> getDistricts(){
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("DHAKA");
        categories.add("FARIDPUR");
        categories.add("GAZIPUR");
        categories.add("GOPALGANJ");
        categories.add("JAMALPUR");
        categories.add("KISHOREGONJ");
        categories.add("MADARIPUR");
        categories.add("MANIKGANJ");
        categories.add("MUNSHIGANJ");
        categories.add("MYMENSINGH");
        categories.add("NARAYANGANJ");
        categories.add("NARSINGDI");
        categories.add("NETRAKONA");
        categories.add("RAJBARI");
        categories.add("SHARIATPUR");
        categories.add("SHERPUR");
        categories.add("TANGAIL");
        categories.add("BARGUNA");
        categories.add("BARISAL");
        categories.add("BHOLA");
        categories.add("JHALOKATI");
        categories.add("PATUAKHALI");
        categories.add("PIROJPUR ");
        categories.add("BANDARBAN");
        categories.add("BRAHMANBARIA");
        categories.add("CHANDPUR");
        categories.add("CHITTAGONG");
        categories.add("COMILLA");
        categories.add("COX'S BAZAR");
        categories.add("COX'S BAZAR");
        categories.add("KHAGRACHHARI");
        categories.add("LAKSHMIPUR");
        categories.add("NOAKHALI");
        categories.add("RANGAMATI ");
        categories.add("BAGERHAT");
        categories.add("CHUADANGA");
        categories.add("JESSORE");
        categories.add("JHENAIDAH");
        categories.add("KHULNA");
        categories.add("KUSHTIA");
        categories.add("MAGURA");
        categories.add("MEHERPUR");
        categories.add("NARAIL");
        categories.add("SATKHIRA");
        categories.add("BOGRA");
        categories.add("CHAPAINABABGANJ");
        categories.add("JOYPURHAT");
        categories.add("PABNA");
        categories.add("NAOGAON");
        categories.add("NATORE");
        categories.add("RAJSHAHI");
        categories.add("SIRAJGANJ");
        categories.add("DINAJPUR");
        categories.add("GAIBANDHA");
        categories.add("KURIGRAM");
        categories.add("LALMONIRHAT");
        categories.add("NILPHAMARI");
        categories.add("PANCHAGARH");
        categories.add("RANGPUR");
        categories.add("THAKURGAON");
        categories.add("HABIGANJ");
        categories.add("MAULVIBAZAR");
        categories.add("SUNAMGANJ");
        categories.add("SYLHET");
        return categories;
    }

}
