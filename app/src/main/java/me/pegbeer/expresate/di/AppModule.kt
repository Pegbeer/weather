package me.pegbeer.expresate.di

import android.content.Context
import androidx.room.Room
import com.pegbeer.domain.WeatherDatabase
import com.pegbeer.domain.dao.WeatherDao
import com.pegbeer.domain.repository.IWeatherRepository
import com.pegbeer.infrastructure.AppRepository
import com.pegbeer.infrastructure.database.RoomRepository
import com.pegbeer.infrastructure.network.IApiService
import com.pegbeer.infrastructure.network.RetrofitRepository
import com.pegbeer.usecases.cities.ISaveCitiesUseCase
import com.pegbeer.usecases.cities.CitiesUseCases
import com.pegbeer.usecases.weather.GetWeatherUseCase
import com.pegbeer.usecases.weather.IGetWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit():IApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(IApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context:Context):WeatherDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "local_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: WeatherDatabase):WeatherDao{
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRepository(
        @ApplicationContext context: Context,
        apiService: IApiService,
        dao: WeatherDao
    ): IWeatherRepository{
        val retrofitRepository = RetrofitRepository(apiService,dao)
        val roomRepository = RoomRepository(dao)
        return AppRepository(context,retrofitRepository,roomRepository)
    }

    @Singleton
    @Provides
    fun provideSaveCitiesUseCase(weatherRepository: IWeatherRepository):ISaveCitiesUseCase{
        return CitiesUseCases(weatherRepository)
    }

    @Singleton
    @Provides
    fun provideGetWeatherUseCase(weatherRepository: IWeatherRepository):IGetWeatherUseCase{
        return GetWeatherUseCase(weatherRepository)
    }


}