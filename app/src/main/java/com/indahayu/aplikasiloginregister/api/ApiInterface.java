package com.indahayu.aplikasiloginregister.api;

import com.indahayu.aplikasiloginregister.MainModel;
import com.indahayu.aplikasiloginregister.Mainmodelgaleri;
import com.indahayu.aplikasiloginregister.Mainmodelpaket;
import com.indahayu.aplikasiloginregister.Mainmodelpesansaya;
import com.indahayu.aplikasiloginregister.Mainmodeltesti;
import com.indahayu.aplikasiloginregister.ModelResponse.KirimTesti;
import com.indahayu.aplikasiloginregister.ModelResponse.LoginResponse;
import com.indahayu.aplikasiloginregister.ModelResponse.PesanResponse;
import com.indahayu.aplikasiloginregister.ModelResponse.RegisterResponse;
import com.indahayu.aplikasiloginregister.ModelResponse.ResponsePOJO;
import com.indahayu.aplikasiloginregister.ModelResponse.UpdateBukti;
import com.indahayu.aplikasiloginregister.ModelResponse.UpdatePassResponse;
import com.indahayu.aplikasiloginregister.ModelResponse.UpdatePesanDP;
import com.indahayu.aplikasiloginregister.ModelResponse.UpdatePesanLunas;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/register.php")
    Call<RegisterResponse> register(
            @Field("username") String username,
            @Field("nama") String nama,
            @Field("password") String password,
            @Field("no_telp") String no

    );

    @FormUrlEncoded
    @POST("api/login.php")
    Call<LoginResponse> login(
            @Field("username") String username,
            @Field("password") String password

    );
    @FormUrlEncoded
    @POST("api/pesan.php")
    Call<PesanResponse> pesan(
            @Field("id_user") String iduser,
            @Field("id_paket") String idpaket,
            @Field("tgl_acara") String tglacara,
            @Field("harga") String harga


    );

    @GET("api/pesanansaya.php")
    Call<List<Mainmodelpesansaya>> getDataPesan(@Query("id_user")String id_user);
    @GET("api/layanan.php") Call<MainModel> getData();
    @GET("api/galeri.php") Call<Mainmodelgaleri> getDataGaleri();
    @GET("api/testimoni.php") Call<Mainmodeltesti> getDataTesti();
    @GET("api/paket.php") Call<Mainmodelpaket> getDatapaket();
    @GET("api/hapusauto.php") Call<KirimTesti> hapusauto();
    @GET("api/hapusautonext.php") Call<KirimTesti> hapusautonext();
    @FormUrlEncoded
    @POST("api/updateuser.php")
    Call<LoginResponse> updateUserAccount(
            @Field("id_user") int userid,
            @Field("username") String userName,
            @Field("nama") String nama,
            @Field("no_telp") String notelp,
            @Field("alamat") String alamat


    );
    @FormUrlEncoded
    @POST("api/pesanupdatedp.php")
    Call<UpdatePesanDP> pesanupdatedp(
            @Field("id_booking") String idbooking,
            @Field("jumlah_bayar") String jumlah
    );

    @FormUrlEncoded
    @POST("api/kirimtesti.php")
    Call<KirimTesti> kirimtesti(
            @Field("id_booking") String idbooking
    );
    @FormUrlEncoded
    @POST("api/kirimtestilagi.php")
    Call<KirimTesti> kirimtestilagi(
            @Field("id_booking") String idbooking,
            @Field("keterangan") String keterangan,
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("api/pesanupdatelunas.php")
    Call<UpdatePesanLunas> pesanupdatelunas(
            @Field("id_booking") String idbooking,
            @Field("harga_paket") String harga
    );

    @FormUrlEncoded
    @POST("api/batalpesan.php")
    Call<KirimTesti> batalpesan(
            @Field("id_booking") String idbookin
    );

    @FormUrlEncoded
    @POST("api/kirimbukti.php")
    Call<UpdateBukti> pesanupdatebukti(
            @Field("id_booking") String idbooking,
            @Field("foto") String foto
    );

    @FormUrlEncoded
    @POST("api/updatepassword.php")
    Call<UpdatePassResponse> updateUserPass(
            @Field("username") String username,
            @Field("current") String currentPassword,
            @Field("new") String newPassword


    );

    @FormUrlEncoded
    @POST("api/upload_image.php")
    Call<ResponsePOJO> uploadImage(
            @Field("id_user") int userid,
            @Field("foto") String encodedImage
    );
}
