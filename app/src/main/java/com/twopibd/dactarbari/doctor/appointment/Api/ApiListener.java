package com.twopibd.dactarbari.doctor.appointment.Api;




import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonElement;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.BasicInfoModel;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberModel;
import com.twopibd.dactarbari.doctor.appointment.Model.CountryModel;
import com.twopibd.dactarbari.doctor.appointment.Model.DepartmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.DoctorModel;
import com.twopibd.dactarbari.doctor.appointment.Model.HospitalModel;
import com.twopibd.dactarbari.doctor.appointment.Model.LoginResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.ProfileResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.RecomentationModel;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.Test;
import com.twopibd.dactarbari.doctor.appointment.Model.TestModel;

import org.json.JSONObject;

import java.util.List;

public class ApiListener {
    public  interface LocationPicked{
        void  onPicked(boolean isPicked);
    }
    public interface drSchedulePostListener {
        void ondrSchedulePostSuccess(StatusMessage data);
        void ondrSchedulePostFailed(String msg);
    }
    public interface drSearchListener {
        void onSearchSuccess(List<SearchModel> data);
        void onSearchFailed(String msg);
    }
    public interface drScheduleAddListener {
        void ondrScheduleAddSuccess(StatusMessage data);
        void ondrScheduleAddFailed(String msg);
    }
    public interface locationReceiveListener {
        void onLocationRetriveSuccess(LatLng latLng);
    }
    public interface drChamberDeleteListener {
        void onChamberDeleteSuccess(StatusMessage data);
        void onChamberDeleteFailed(String msg);
    }
    public interface drScheduleDeleteListener {
        void onScheduleDeleteSuccess(StatusMessage data);
        void onScheduleDeleteFailed(String msg);
    }
    public interface drChamberDownloadListener {
        void ondrChamberDownloadSuccess(List<ChamberModel> data);
        void onChamberDownloadFailed(String msg);
    }
    public interface doctorSearchListener {
        void onSearchSuccess(List<DoctorModel> list);
        void onSuccessFailed(String msg);
    }
    public interface profileGet {
        void onProfileSuccess(String list);
        void onProfileFailed(String msg);
    }
    public interface drProfileUpdate {
        void ondrProfileUpdateSuccess(StatusMessage list);
        void ondrProfileUpdateFailed(String msg);
    }
    public interface presCriptionUploadListener {
        void onPrescriptionAddSuccess(StatusMessage list);
        void onPrescriptionAddFailed(String msg);
    }
    public interface patientProfileUpdate {
        void onpatientProfileUpdateSuccess(StatusMessage list);
        void onpatientProfileUpdateFailed(String msg);
    }
    public interface emailCheckListener {
        void onEmailCheckSuccess(StatusMessage response);
        void onEmailCheckFailed(String msg);
    }
    public interface deletePrescriptionListener {
        void onPrescriptionDeleteSuccess(StatusMessage response);
        void onPrescriptionDeleteFailed(String msg);
    }
    public interface countryListDownlaodListener {
        void onCountryDownloadSuccess(List<CountryModel> list);
        void onCountryDownloadFailed(String msg);
    }
    public interface HospitalListDownlaodListener {
        void onHospitalLisDownloadSuccess(List<HospitalModel> list);
        void onHospitalLisDownloadFailed(String msg);
    }
    public interface DepartmentListDownlaodListener {
        void onDepartmentLisDownloadSuccess(List<DepartmentModel> list);
        void onDepartmentLisDownloadFailed(String msg);
    }
    public interface basicInfoDownloadListener {
        void onBasicInfoDownloadSuccess(BasicInfoModel data);

        void onBasicInfoDownloadFailed(String msg);
    }

    public interface drBasicInfoPostListener {
        void onBasicInfoPostSuccess(StatusMessage data);
        void onBasicInfoPostFailed(String msg);
    }
    public interface patientSignUpListener {
        void onPatientSignUpPostSuccess(StatusMessage data);
        void onPatientSignUpPostFailed(String msg);
    }
    public interface StringRequestListener {
        void onStringPostSuccess(String data);
        void onStringPostFailed(String msg);
    }
    public interface CheckMobileListener {
        void onMobileCheckSuccess(StatusResponse status);

        void onMobileCheckFailed(String msg);
    }

    public interface LoginUserListener {
        void onUserLoginSuccess(String status);

        void onUserLoginFailed(String msg);
    }

    public interface appoinetmentPOstListener {
        void onAppointmentPostSuccess(StatusResponse status);

        void onAppointmentPostFailed(String msg);
    }

    public interface appoinetmentsDownloadListener {
        void onAppointmentDownloadSuccess(AppointmentResponse status);

        void onAppointmentDownloadFailed(String msg);
    }

    public interface dataDownloadListener {
        void onDownloaded(List<AppointmentModel> status);
    }

    public interface patientAllDataDownloadListener {
        void onDownloaded(AppointmentResponse status);
    }
    public interface patientNotificationDataDownloadListener {
        void onNotificationDownloaded(List<RecomentationModel> status);
    }

    public interface appointmentStateChangeListener {
        void onAppointmentChangeSuccess(StatusResponse list);
        void onPppointmentChangeFailed(String msg);
    }

    public interface testNamesDownloadListener {
        void ontestNamesDownloadSuccess(List<TestModel> data);
        void ontestNamesDownloadFailed(String msg);
    }

    public interface recomendationTestPostListener {
        void onrecomendationTestPostSuccess(StatusResponse response);
        void onrecomendationTestPostFailed(String msg);
    }
}
