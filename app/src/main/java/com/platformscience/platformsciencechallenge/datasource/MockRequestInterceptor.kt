package com.platformscience.platformsciencechallenge.datasource

import android.content.Context
import com.platformscience.platformsciencechallenge.common.readFileFromAssets
import okhttp3.*

//Used this class to load the json file with shipments and drivers
//Based on this article: https://medium.com/@kunalchaubal/toggle-between-api-endpoint-and-local-json-with-a-single-line-of-code-change-android-6088284822ae
class MockRequestInterceptor(private val context: Context) : Interceptor {
    companion object {
        private val JSON_MEDIA_TYPE = MediaType.parse("application/json")
        private const val MOCK = "mock"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val header = request.header(MOCK)

        if (header != null) {
            val filename = request.url().pathSegments().last()
            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .message("")
                .code(200)
                .body(
                    ResponseBody.create(
                        JSON_MEDIA_TYPE,
                        context.readFileFromAssets("$filename.json")
                    )
                )
                .build()
        }
        return chain.proceed(request.newBuilder().removeHeader(MOCK).build())
    }
}
