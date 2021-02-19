package com.example.testnavermap.api

import com.example.testnavermap.api.ReverseGeocodingResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * 리버스 지오코딩 서비스.
 */
interface ReverseGeocodingService {
    /**
     * 리버스 지오코딩.
     *
     * @param clientId 클라이언트 ID.
     * @param clientSecret 클라이언트 시크릿.
     * @param coord 좌표. `"$longitude,$latitude"` 포맷.
     * @return 응답 결과.
     */
    @GET("map-reversegeocode/v2/gc?output=json")
    suspend fun gc(
        @Header("X-NCP-APIGW-API-KEY-ID") clientId: String,
        @Header("X-NCP-APIGW-API-KEY") clientSecret: String,
        @Query("coords") coord: String
    ): ReverseGeocodingResponse
}
