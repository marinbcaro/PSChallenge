package com.platformscience.platformsciencechallenge.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.platformscience.platformsciencechallenge.cache.AppDatabase
import com.platformscience.platformsciencechallenge.cache.AppDatabase.Companion.DATABASE_NAME
import com.platformscience.platformsciencechallenge.cache.DriverDao
import com.platformscience.platformsciencechallenge.cache.ShipmentDao
import com.platformscience.platformsciencechallenge.datasource.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlatformScienceModule {
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val clientRequest = originalRequest.newBuilder()
                    .build()
                chain.proceed(clientRequest)
            }
            .addInterceptor(MockRequestInterceptor(context))
            .build()
    }

    @Provides
    fun provideDeliveryService(
        okHttpClient: OkHttpClient
    ): DeliveryService {
        return Retrofit.Builder()
            .baseUrl("http://localhost/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeliveryService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDriverDao(database: AppDatabase): DriverDao {
        return database.getDriverDao()
    }

    @Singleton
    @Provides
    fun provideShipmentDao(database: AppDatabase): ShipmentDao {
        return database.getShipmentDao()
    }

    @Provides
    fun provideRepository(
        service: DeliveryService
    ): DeliveryRepository {
        return DeliveryRepository(service)
    }

    @Provides
    @Singleton
    fun provideDriversRepository(
        dao: DriverDao, service: DeliveryService
    ): GetDriversRepository {
        return GetDriversRepository(dao, service)
    }

    @Provides
    @Singleton
    fun provideGetDrivers(
        repository: GetDriversRepository,
        shipmentsRepository: GetShipmentsRepository
    ): GetDrivers {
        return GetDrivers(
            driversRepository = repository,
            shipmentRepository = shipmentsRepository
        )
    }

    @Provides
    @Singleton
    fun provideShipmentsRepository(dao: ShipmentDao): GetShipmentsRepository {
        return GetShipmentsRepository(shipmentDao = dao)
    }
}

