package com.twopibd.dactarbari.doctor.appointment.Api;

import android.support.annotation.NonNull;

import com.google.gson.JsonElement;
import com.twopibd.dactarbari.doctor.appointment.Activity.DrSignUpActivity;
import com.twopibd.dactarbari.doctor.appointment.Activity.PatientProfileUpdateActivity;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Api {

    private static Api dataManager = null;

    public static Api getInstance() {

        if (dataManager == null) {
            dataManager = new Api();
        }

        return dataManager;
    }

    public void loginUser(String mobile, String password, final ApiListener.LoginUserListener loginUserListener) {

        ApiClient.getApiInterface().login(mobile, password).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                if (response != null) {
                    loginUserListener.onUserLoginSuccess(response.body().toString());

                }

            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                loginUserListener.onUserLoginFailed(t.getLocalizedMessage());
            }
        });
    }

    public void searchDoctor(String dr_name, String hospital_name, String specialist, String city, String day, final ApiListener.doctorSearchListener doctorSearchListener) {
        ApiClient.getApiInterface().searchChamber(dr_name, hospital_name, specialist, city, day).enqueue(new Callback<List<DoctorModel>>() {
            @Override
            public void onResponse(Call<List<DoctorModel>> call, Response<List<DoctorModel>> response) {


            }

            @Override
            public void onFailure(Call<List<DoctorModel>> call, Throwable t) {
                doctorSearchListener.onSuccessFailed(t.getLocalizedMessage());

            }
        });

    }

    public void getProfile(String key, String id, final ApiListener.profileGet listener) {
        ApiClient.getApiInterface().getProfileInfo(key, id).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //listener.onProfileSuccess(response.body().toString());
                if (!response.isSuccessful()) {
                    listener.onProfileFailed("Unsuccessfull");
                } else if (response == null) {
                    listener.onProfileFailed("Null");
                } else if (response.isSuccessful()) {
                    listener.onProfileSuccess(response.body().toString());
                } else {
                    listener.onProfileFailed("Unknown error");
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                listener.onProfileFailed(t.getLocalizedMessage());

            }
        });

    }

    public void downloadCountry(final ApiListener.countryListDownlaodListener listener) {
        ApiClient.getApiInterface().getCountryList().enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        listener.onCountryDownloadSuccess(response.body());

                    } else listener.onCountryDownloadFailed("Null array");

                } else {
                    listener.onCountryDownloadFailed("Unsuccessfull");

                }

            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                listener.onCountryDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void DoctorProfileUpdate(String token,
                                    String user_type,
                                    String name,
                                    String gender,
                                    String email,
                                    String password,
                                    String mobile,
                                    String country,
                                    String house_no,
                                    String street_address,
                                    String area,
                                    String city,
                                    String postcode,
                                    String type,
                                    String qualification,
                                    String current_hospital,
                                    String department,
                                    String previous_hospital,
                                    String id, final ApiListener.drProfileUpdate listener) {

        ApiClient.getApiInterface().updateDrInfo(token, user_type,
                name,
                gender,
                email,
                password,
                mobile,
                country,
                house_no,
                street_address,
                area,
                city,
                postcode,
                type,
                qualification,
                current_hospital,
                department,
                previous_hospital,
                id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        listener.ondrProfileUpdateSuccess(response.body());

                    } else listener.ondrProfileUpdateFailed("Null array");

                } else {
                    listener.ondrProfileUpdateFailed("Unsuccessfull");

                }
            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.ondrProfileUpdateFailed(t.getLocalizedMessage());

            }
        });

    }

    public void downloadHospitals(final ApiListener.HospitalListDownlaodListener listener) {
        ApiClient.getApiInterface().getHospitalList().enqueue(new Callback<List<HospitalModel>>() {
            @Override
            public void onResponse(Call<List<HospitalModel>> call, Response<List<HospitalModel>> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        listener.onHospitalLisDownloadSuccess(response.body());

                    } else listener.onHospitalLisDownloadFailed("Null array");

                } else {
                    listener.onHospitalLisDownloadFailed("Unsuccessfull");

                }

            }

            @Override
            public void onFailure(Call<List<HospitalModel>> call, Throwable t) {
                listener.onHospitalLisDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void downloadDepartments(final ApiListener.DepartmentListDownlaodListener listener) {
        ApiClient.getApiInterface().getDepartmentList().enqueue(new Callback<List<DepartmentModel>>() {
            @Override
            public void onResponse(Call<List<DepartmentModel>> call, Response<List<DepartmentModel>> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        listener.onDepartmentLisDownloadSuccess(response.body());

                    } else listener.onDepartmentLisDownloadFailed("Null array");

                } else {
                    listener.onDepartmentLisDownloadFailed("Unsuccessfull");

                }

            }

            @Override
            public void onFailure(Call<List<DepartmentModel>> call, Throwable t) {
                listener.onDepartmentLisDownloadFailed(t.getLocalizedMessage());

            }
        });

    }


    public void signUpDoctor(RequestBody user_type,
                             RequestBody dr_name,
                             RequestBody gender,
                             RequestBody email,
                             RequestBody password,
                             RequestBody mobile,
                             RequestBody country,

                             RequestBody house,
                             RequestBody street,
                             RequestBody area,
                             RequestBody city,
                             RequestBody post_code,

                             RequestBody type,
                             RequestBody qualification,
                             RequestBody current_hospital,
                             RequestBody department,
                             RequestBody prev_hospital,
                             MultipartBody.Part photo,
                             final ApiListener.drBasicInfoPostListener listener) {
        String TYPE_DOCTOR = "doctor";
        ApiClient.getApiInterface().signUpDoctor(user_type, dr_name, gender, email, password, mobile, country, house, street, area, city, post_code, type, qualification, current_hospital, department, prev_hospital, photo).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onBasicInfoPostSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onBasicInfoPostFailed(t.getLocalizedMessage());

            }
        });

    }

    public void signUpPatient(String user_type,
                              String pa_name,
                              String gender,
                              String password,
                              String mobile,
                              String email,
                              String country,
                              String house,
                              String street,
                              String area,
                              String city,
                              String post_code,
                              String problems,
                              MultipartBody photo,
                              final ApiListener.patientSignUpListener listener) {
        ApiClient.getApiInterface().signUpPatient(user_type, pa_name, gender, password, mobile, email, country, house, street, area, city, post_code, problems, photo).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onPatientSignUpPostSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onPatientSignUpPostFailed(t.getLocalizedMessage());

            }
        });

    }

    public void upPhoto(RequestBody doctor, MultipartBody.Part photo, final ApiListener.StringRequestListener listener) {
        String TYPE_DOCTOR = "patient";
        ApiClient.getApiInterface().postPhoto(doctor, photo).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                listener.onStringPostSuccess(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onStringPostFailed(t.getLocalizedMessage());

            }
        });

    }

    public void downloadBasicInfo(final ApiListener.basicInfoDownloadListener listener) {
        ApiClient.getApiInterface().getBasicInfo().enqueue(new Callback<BasicInfoModel>() {
            @Override
            public void onResponse(Call<BasicInfoModel> call, Response<BasicInfoModel> response) {
                listener.onBasicInfoDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<BasicInfoModel> call, Throwable t) {
                listener.onBasicInfoDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void isEmailUnique(String email, final ApiListener.emailCheckListener listener) {
        ApiClient.getApiInterface().isEmailUnique(email).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onEmailCheckSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onEmailCheckFailed(t.getLocalizedMessage());

            }
        });

    }

    public void deletePrescription(String key, String id, final ApiListener.deletePrescriptionListener listener) {
        ApiClient.getApiInterface().deletePrescription(key, id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onPrescriptionDeleteSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onPrescriptionDeleteFailed(t.getLocalizedMessage());

            }
        });

    }

    public void deleteChamber(String key, String chamber_id, final ApiListener.drChamberDeleteListener listener) {
        ApiClient.getApiInterface().deleteChamber(key, chamber_id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onChamberDeleteSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onChamberDeleteFailed(t.getLocalizedMessage());

            }
        });

    }

    public void deleteSchedule(String key, String schedule_id, final ApiListener.drScheduleDeleteListener listener) {
        ApiClient.getApiInterface().deleteScheduleDay(key, schedule_id).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onScheduleDeleteSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onScheduleDeleteFailed(t.getLocalizedMessage());

            }
        });

    }

    public void AddSchedule(String key, String chamber_id, String schedule_info, final ApiListener.drScheduleAddListener listener) {
        ApiClient.getApiInterface().addScheduleDr(key, chamber_id, schedule_info).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.ondrScheduleAddSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.ondrScheduleAddFailed(t.getLocalizedMessage());

            }
        });

    }

    public void drChambers(String key, String id, final ApiListener.drChamberDownloadListener listener) {
        ApiClient.getApiInterface().drChamber(key, id).enqueue(new Callback<List<ChamberModel>>() {
            @Override
            public void onResponse(Call<List<ChamberModel>> call, Response<List<ChamberModel>> response) {
                listener.ondrChamberDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<ChamberModel>> call, Throwable t) {
                listener.onChamberDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void allDoctor(final ApiListener.drSearchListener listener) {
        ApiClient.getApiInterface().searchResults().enqueue(new Callback<List<SearchModel>>() {
            @Override
            public void onResponse(Call<List<SearchModel>> call, Response<List<SearchModel>> response) {
                listener.onSearchSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<SearchModel>> call, Throwable t) {
                listener.onSearchFailed(t.getLocalizedMessage());

            }
        });

    }

    public void SearchByName(String name, final ApiListener.drNameSearchListener listener) {
        ApiClient.getApiInterface().searchNameResults(name).enqueue(new Callback<List<SearchModel>>() {
            @Override
            public void onResponse(Call<List<SearchModel>> call, Response<List<SearchModel>> response) {
                listener.ondrNameSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<SearchModel>> call, Throwable t) {
                listener.ondrNameFailed(t.getLocalizedMessage());

            }
        });

    }

    public void chamberDetails(String chamber_id, final ApiListener.ChamberDetailsDownloadListener listener) {
        ApiClient.getApiInterface().getChamberInfo(chamber_id).enqueue(new Callback<ChamberInfo>() {
            @Override
            public void onResponse(Call<ChamberInfo> call, Response<ChamberInfo> response) {
                listener.onChamberDetailsDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<ChamberInfo> call, Throwable t) {
                listener.onChamberDetailsDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void getScheduleSlot(String schedule_id, String date, final ApiListener.chamberSlotListener listener) {
        ApiClient.getApiInterface().getChamberSlots(schedule_id, date).enqueue(new Callback<List<Slot>>() {
            @Override
            public void onResponse(Call<List<Slot>> call, Response<List<Slot>> response) {
                listener.onchamberSlotSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<Slot>> call, Throwable t) {
                listener.onchamberSlotFailed(t.getLocalizedMessage());

            }
        });

    }

    public void setDrSchedule(String key, String id, String address, String visit_fee, String days, String chamber_latitude, String chamber_longitude, final ApiListener.drSchedulePostListener listener) {
        ApiClient.getApiInterface().setDrSchedule(key, id, address, visit_fee, days, chamber_latitude, chamber_longitude).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.ondrSchedulePostSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.ondrSchedulePostFailed(t.getLocalizedMessage());

            }
        });

    }

    public void AddPrescription(String key, String id, MultipartBody requestBody, final ApiListener.presCriptionUploadListener listener) {
        ApiClient.getApiInterface().addPrescription(key, requestBody).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onPrescriptionAddSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onPrescriptionAddFailed(t.getLocalizedMessage());

            }
        });

    }

    public void checkMobile(String mobile, final ApiListener.CheckMobileListener checkMobileListener) {

        ApiClient.getApiInterface().checkMobile(mobile).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    checkMobileListener.onMobileCheckSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                checkMobileListener.onMobileCheckFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postAppointment(String patient_id, String chamber_id, String dr_id, String appointment_for, String phone, String problems, String date, final ApiListener.appoinetmentPOstListener listener) {

        ApiClient.getApiInterface().postAppointment(patient_id, chamber_id, dr_id, appointment_for, phone, problems, date).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    listener.onAppointmentPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                listener.onAppointmentPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void postRecommendationTest(String appointment_id, String test_id, final ApiListener.recomendationTestPostListener listener) {

        ApiClient.getApiInterface().postRecommenTest(test_id, appointment_id).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    listener.onrecomendationTestPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                listener.onrecomendationTestPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAppointmentsByDoctor(String dr_id, final ApiListener.appoinetmentsDownloadListener listener) {

        ApiClient.getApiInterface().myAppointmentsbyDoctor(dr_id).enqueue(new Callback<AppointmentResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppointmentResponse> call, @NonNull Response<AppointmentResponse> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<AppointmentResponse> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAppointmentsBypatient(String id, final ApiListener.appoinetmentsDownloadListener listener) {

        ApiClient.getApiInterface().myAppointmentsbyPatient(id).enqueue(new Callback<AppointmentResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppointmentResponse> call, @NonNull Response<AppointmentResponse> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<AppointmentResponse> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void changeStatus(String id, String status, final ApiListener.appointmentStateChangeListener listener) {

        ApiClient.getApiInterface().changeAppointmentStatus(id, status).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatusResponse> call, @NonNull Response<StatusResponse> response) {
                if (response != null) {
                    listener.onAppointmentChangeSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusResponse> call, @NonNull Throwable t) {
                listener.onPppointmentChangeFailed(t.getLocalizedMessage());
            }
        });
    }

    public void downloadTestNames(final ApiListener.testNamesDownloadListener listener) {
        ApiClient.getApiInterface().getTestNames().enqueue(new Callback<List<TestModel>>() {
            @Override
            public void onResponse(Call<List<TestModel>> call, Response<List<TestModel>> response) {
                listener.ontestNamesDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<TestModel>> call, Throwable t) {
                listener.ontestNamesDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void updatePatientProfile(String token, String user_type, String name, String gen, String password, String mobile, String country, String house, String street, String area, String city, String postCode, String problems, String email, String id, final ApiListener.patientProfileUpdate listener) {
        ApiClient.getApiInterface().updatePatientProfile(token, user_type,
                name,
                gen, password,
                mobile,
                country,
                house,
                street,
                area,
                city,
                postCode,
                problems,
                email,
                id
        ).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                listener.onpatientProfileUpdateSuccess(response.body());

            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                listener.onpatientProfileUpdateFailed(t.getLocalizedMessage());

            }
        });

    }
}
