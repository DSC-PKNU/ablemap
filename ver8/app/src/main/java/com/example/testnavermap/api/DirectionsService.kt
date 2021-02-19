package com.example.testnavermap.api

import com.example.testnavermap.api.DirectionsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * 경로 탐색 서비스.
 */
interface DirectionsService {
    /**
     * 자동차 경로 탐색.
     *
     * @param clientId 클라이언트 ID.
     * @param clientSecret 클라이언트 시크릿.
     * @param start 출발지. `"$longitude,$latitude"` 포맷.
     * @param goal 목적지. `"$longitude,$latitude"` 포맷
     * @return 응답 결과.
     */
    @GET("map-direction/v1/driving")
    suspend fun driving(
        @Header("X-NCP-APIGW-API-KEY-ID") clientId: String,
        @Header("X-NCP-APIGW-API-KEY") clientSecret: String,
        @Query("start") start: String,
        @Query("goal") goal: String
    ): DirectionsResponse
}
