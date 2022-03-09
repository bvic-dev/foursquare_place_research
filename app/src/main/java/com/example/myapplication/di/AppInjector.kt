package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.PlaceConverters
import com.example.myapplication.data.local.entity.FavoritePlacesDatabase
import com.example.myapplication.data.remote.api.PlacesApi
import com.example.myapplication.data.util.GsonParser
import com.example.myapplication.domain.repository.PlacesRepository
import com.example.myapplication.domain.repository.PlacesRepositoryImpl
import com.example.myapplication.domain.use_case.AddNewFavoritePlace
import com.example.myapplication.domain.use_case.DeleteFavoritePlace
import com.example.myapplication.domain.use_case.GetFavoritePlaces
import com.example.myapplication.domain.use_case.GetPlaces
import com.example.myapplication.domain.use_case.PlaceUsesCases
import com.example.myapplication.features.shared.helper.GeocoderHelper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppInjector {

    @Provides
    @Singleton
    fun providePlacesApi(): PlacesApi {
        return Retrofit.Builder()
            .baseUrl(PlacesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()
            )
            .build()
            .create(PlacesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoritePlacesDatabase(
        @ApplicationContext appContext: Context
    ): FavoritePlacesDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoritePlacesDatabase::class.java,
            FavoritePlacesDatabase.DATABASE_NAME
        ).addTypeConverter(PlaceConverters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun providePlacesRepository(
        api: PlacesApi,
        db: FavoritePlacesDatabase
    ): PlacesRepository {
        return PlacesRepositoryImpl(api = api, dao = db.dao)
    }

    @Provides
    @Singleton
    fun providePlacesUseCases(repository: PlacesRepository): PlaceUsesCases {
        return PlaceUsesCases(
            getPlaces = GetPlaces(repository = repository),
            getFavoritePlaces = GetFavoritePlaces(repository = repository),
            addNewFavoritePlace = AddNewFavoritePlace(repository = repository),
            deleteFavoritePlace = DeleteFavoritePlace(repository = repository)
        )
    }

    @Provides
    @Singleton
    fun provideGeocoderHelper(@ApplicationContext appContext: Context): GeocoderHelper {
        return GeocoderHelper(
            context = appContext
        )
    }
}
