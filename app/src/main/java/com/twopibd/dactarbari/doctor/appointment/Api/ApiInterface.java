package com.twopibd.dactarbari.doctor.appointment.Api;


import com.google.gson.JsonElement;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModels;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.BasicInfoModel;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberModel;
import com.twopibd.dactarbari.doctor.appointment.Model.CountryModel;
import com.twopibd.dactarbari.doctor.appointment.Model.DepartmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.DoctorModel;
import com.twopibd.dactarbari.doctor.appointment.Model.HospitalModel;
import com.twopibd.dactarbari.doctor.appointment.Model.LoginResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.ProfileResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.Model.Slot;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.Test;
import com.twopibd.dactarbari.doctor.appointment.Model.TestModel;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TAOHID on 1/21/2018.
 */

public interface ApiInterface {


    @GET("global/api/AppsLogin/{userName}/{password}")
    Call<JsonElement> loginNishi(@Path("userName") String userName, @Path("password") String password);


    @FormUrlEncoded
    @POST("searchDoctor.php")
    Call<List<DoctorModel>> searchChamber(@Field("dr_name") String dr_name,
                                          @Field("hospital_name") String hospital_name,
                                          @Field("specialist") String specialist,
                                          @Field("city") String city,
                                          @Field("day") String day);

    @FormUrlEncoded
    @POST("getMyAppointmentsDoctor.php")
    Call<AppointmentResponse> myAppointmentsbyDoctor(@Field("dr_id") String dr_id);


    @GET("country-list")
    Call<List<CountryModel>> getCountryList();

    @GET("hospital-list")
    Call<List<HospitalModel>> getHospitalList();

    @GET("department-list")
    Call<List<DepartmentModel>> getDepartmentList();

    @FormUrlEncoded
    @POST("getMyAppointments.php")
    Call<AppointmentResponse> myAppointmentsbyPatient(@Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("login")
    Call<JsonElement> login(@Field("email") String email, @Field("password") String password);

    @GET("check-email-uniqueness/{email}")
    Call<StatusMessage> isEmailUnique(@Path("email") String email);

    @GET("delete-prescription-photo/{id}")
    Call<StatusMessage> deletePrescription(@Header("Authorization") String token, @Path("id") String id);

    @FormUrlEncoded
    @POST("view-account-info")
    Call<JsonElement> getProfileInfo(@Header("Authorization") String token, @Field("id") String id, @Field("patient") String patient);

    @FormUrlEncoded
    @POST("doctor-account-info")
    Call<JsonElement> getDoctorProfileInfo(@Header("Authorization") String token, @Field("id") String id);

    @FormUrlEncoded
    @POST("delete-doctor-chamber")
    Call<StatusMessage> deleteChamber(@Header("Authorization") String token, @Field("chamber_id") String chamber_id);

    @FormUrlEncoded
    @POST("add-doctor-schedule")
    Call<StatusMessage> addScheduleDr(@Header("Authorization") String token, @Field("chamber_id") String chamber_id, @Field("schedule_info") String schedule_info);

    @FormUrlEncoded
    @POST("delete-doctor-schedule")
    Call<StatusMessage> deleteScheduleDay(@Header("Authorization") String token, @Field("schedule_id") String schedule_id);


    @POST("search-doctor")
    Call<List<SearchModel>> searchResults();

    @FormUrlEncoded
    @POST("search-doctor")
    Call<List<SearchModel>> searchDepartmentDoctors(@Header("Authorization") String token, @Field("department_id") String department_id);
    @FormUrlEncoded
    @POST("search-doctor")
    Call<List<SearchModel>> searchHospitalDoctors(@Header("Authorization") String token, @Field("hospital_id") String department_id);


    @FormUrlEncoded
    @POST("search-doctor")
    Call<List<SearchModel>> searchNameResults(@Field("doctor_name") String doctor_name);

    @FormUrlEncoded
    @POST("chamber-schedule-list")
    Call<ChamberInfo> getChamberInfo(@Field("chamber_id") String chamber_id);

    @FormUrlEncoded
    @POST("get-doctor-slots")
    Call<List<Slot>> getChamberSlots(@Field("schedule_id") String schedule_id, @Field("date") String date);

    @FormUrlEncoded
    @POST("book-an-appointment")
    Call<StatusMessage> postAppointmentPatient(@Header("Authorization") String token,
                                               @Field("schedule_id") String schedule_id,
                                               @Field("slot_start_time") String slot_start_time,
                                               @Field("slot_end_time") String slot_end_time,
                                               @Field("patient_id") String patient_id,
                                               @Field("appointment_for") String appointment_for,
                                               @Field("current_problems") String current_problems,
                                               @Field("date") String date);

    @FormUrlEncoded
    @POST("appointment-list")
    Call<List<AppointmentModels>> getMyAppointments(@Header("Authorization") String token,
                                                    @Field("id") String id,
                                                    @Field("user_type") String user_type,
                                                    @Field("status") String status);
    @FormUrlEncoded
    @POST("update-appointment-status")
    Call<StatusMessage> changeAppintmentStatus(@Header("Authorization") String token,
                                                    @Field("appointment_id") String appointment_id,
                                                    @Field("status") String status);


    @FormUrlEncoded
    @POST("add-doctor-chamber-and-schedule")
    Call<StatusMessage> setDrSchedule(@Header("Authorization") String token,
                                      @Field("doctor_id") String id,
                                      @Field("chamber_address") String address,
                                      @Field("fee") String visit_fee,
                                      @Field("schedule_info") String days,
                                      @Field("chamber_latitude") String chamber_latitude,
                                      @Field("chamber_longitude") String chamber_longitude
    );

    @POST("add-prescription-photo/9")
    Call<StatusMessage> addPrescription(@Header("Authorization") String token, @Body RequestBody image);

    @FormUrlEncoded
    @POST("register")
    Call<AppointmentResponse> signUp(@Field("patient_id") String patient_id);

    @FormUrlEncoded
    @POST("view-doctor-chamber-and-schedule")
    Call<List<ChamberModel>> drChamber(@Header("Authorization") String token, @Field("doctor_id") String doctor_id);


    @FormUrlEncoded
    @POST("postRecommenTest.php")
    Call<StatusResponse> postRecommenTest(@Field("test_id") String test_id, @Field("appointment_id") String appointment_id);

    @FormUrlEncoded
    @POST("update-account-info/{id}")
    Call<StatusMessage> updateDrInfo(@Header("Authorization") String token,
                                     @Field("user_type") String user_type,
                                     @Field("name") String name,
                                     @Field("gender") String gender,
                                     @Field("email") String email,
                                     @Field("password") String password,
                                     @Field("mobile") String mobile,
                                     @Field("country") String country,
                                     @Field("house_no") String house_no,
                                     @Field("street_address") String street_address,
                                     @Field("area") String area,
                                     @Field("city") String city,
                                     @Field("postcode") String postcode,
                                     @Field("type") String type,
                                     @Field("qualification") String qualification,
                                     @Field("current_hospital") String current_hospital,
                                     @Field("department") String department,
                                     @Field("previous_hospital") String previous_hospital,
                                     @Path("id") String id);


    @FormUrlEncoded
    @POST("update-account-info/{id}")
    Call<StatusMessage> updatePatientProfile(@Header("Authorization") String token,
                                             @Field("user_type") String user_type,
                                             @Field("name") String name,
                                             @Field("gender") String gender,
                                             @Field("password") String password,
                                             @Field("mobile") String mobile,
                                             @Field("country") String country,
                                             @Field("house_no") String house_no,
                                             @Field("street_address") String street_address,
                                             @Field("area") String area,
                                             @Field("city") String city,
                                             @Field("postcode") String postcode,
                                             @Field("current_problems") String current_problems,
                                             @Field("email") String email,
                                             @Path("id") String id);


    @GET("getBasicInfo.php")
    Call<BasicInfoModel> getBasicInfo();

    @GET("getAllTestNames.php")
    Call<List<TestModel>> getTestNames();


    @POST("register")
    Call<StatusMessage> signUpPatient(@Query("user_type") String user_type,
                                      @Query("name") String name,
                                      @Query("gender") String gender,
                                      @Query("password") String password,
                                      @Query("mobile") String mobile,
                                      @Query("country") String country,
                                      @Query("house_no") String house_no,
                                      @Query("street_address") String street,
                                      @Query("area") String area,
                                      @Query("city") String city,
                                      @Query("postcode") String post_code,
                                      @Query("email") String email);


    @Multipart
    @POST("register")
    Call<StatusMessage> signUpDoctor(@Part("user_type") RequestBody user_type,
                                     @Part("name") RequestBody name,
                                     @Part("gender") RequestBody gender,
                                     @Part("email") RequestBody email,
                                     @Part("password") RequestBody password,
                                     @Part("mobile") RequestBody mobile,
                                     @Part("country") RequestBody country,
                                     @Part("house_no") RequestBody house_no,
                                     @Part("street_address") RequestBody street,
                                     @Part("area") RequestBody area,
                                     @Part("city") RequestBody city,
                                     @Part("postcode") RequestBody post_code,
                                     @Part("type") RequestBody type,
                                     @Part("qualification") RequestBody qualitifcation,
                                     @Part("current_hospital") RequestBody current_hospital,
                                     @Part("department") RequestBody current_department,
                                     @Part("previous_hospital") RequestBody previous_hospital,
                                     @Part MultipartBody.Part image);

    @Multipart
    @POST("register")
    Call<String> postPhoto(@Part("user_type") RequestBody doctor, @Part MultipartBody.Part image);


    @FormUrlEncoded
    @POST("postAppointments.php")
    Call<StatusResponse> postAppointment(@Field("patient_id") String patient_id,
                                         @Field("chamber_id") String chamber_id,
                                         @Field("dr_id") String dr_id,
                                         @Field("appointment_for") String appointment_for,
                                         @Field("phone") String phon,
                                         @Field("problems") String problems,
                                         @Field("date") String date);

    @FormUrlEncoded
    @POST("checkMobile.php")
    Call<StatusResponse> checkMobile(@Field("mobile") String mobile);

//    @FormUrlEncoded
//    @POST("login.php")
//    Call<LoginResponse> login(@Field("mobile") String mobile,
//                              @Field("password") String password);

    @FormUrlEncoded
    @POST("changeAppointmentStatus.php")
    Call<StatusResponse> changeAppointmentStatus(@Field("id") String id,
                                                 @Field("status") String status);
/*
    @POST("saveFile")
    Call<FileUploadResponse> sendFileToServer(@Header("Authorization") String auth, @Query("type") String type, @Query("directoryPath") String directoryPath, @Body RequestBody multipartFile);

    @Multipart
    @POST("saveFile")
    Call<FileUploadResponse> sendBigFileToServer(@Header("Authorization") String auth, @Query("type") String type, @Query("directoryPath") String directoryPath, @Part MultipartBody.Part multipartFile);

    @POST("saveFile")
    Call<FileUploadResponse> getFileFromServer(@Header("Authorization") String auth, @Query("type") String type);

    @POST("register")
    Call<RegistrationResponse> registration(@Body RegistrationDataModel registrationDataModel);

    @POST("login")
    Call<LoginResponse> login(@Body LoginDataModel loginDataModel);

    @POST("details")
    Call<ProfileResponse> getProfile(@Header("Authorization") String auth);

    @POST("editUser")
    Call<CommonResponse> editUser(@Header("Authorization") String auth, @Body EditUserModel editUserModel);

    @POST("contactList")
    Call<ContactPostResponse> postContactList(@Header("Authorization") String auth, @Body ContactPostModel contactPostModel);

    @POST("callLog")
    Call<CallLogPostResponse> postCallLog(@Header("Authorization") String auth, @Body CallLogPostModel callLogPostModel);

    @POST("smsHistory")
    Call<SmsHistoryPostResponse> postSmsHistory(@Header("Authorization") String auth, @Body SmsHistoryPostModel smsHistoryPostModel);

    @POST("logout")
    Call<CommonResponse> logout(@Header("Authorization") String auth);

    @POST("passwordChange")
    Call<CommonResponse> passwordChange(@Header("Authorization") String auth, @Body ChangePasswordModel changePasswordModel);

    @POST("folderList")
    Call<FolderListResponse> folderList(@Header("Authorization") String auth, @Query("folderCategory") String folderCategory);

    @POST("noteSend")
    Call<NoteResponse> noteSend(@Header("Authorization") String auth, @Body NoteModel noteModel);

    @POST("noteSoftDeleted")
    Call<NoteDeleteResponse> noteDelete(@Header("Authorization") String auth, @Query("id") String id);

    @POST("todoSend")
    Call<TodoResponse> todoSend(@Header("Authorization") String auth, @Body TodoModel todoModel);

    @POST("todoSoftDeleted")
    Call<TodoDeleteResponse> todoDelete(@Header("Authorization") String auth, @Query("id") String id);

    @POST("UserFolderList")
    Call<UserFileFolderListResponse> userFileFolderList(@Header("Authorization") String auth, @Query("type") String type);

    @Multipart
    @POST("deleteFile")
    Call<FileDeleteResponse> fileDelete(@Header("Authorization") String auth, @Part("type") RequestBody type, @Part("id") RequestBody id);
*/
}