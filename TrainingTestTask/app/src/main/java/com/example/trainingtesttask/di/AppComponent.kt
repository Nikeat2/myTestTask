package com.example.trainingtesttask.di

import android.content.Context
import androidx.room.Room
import com.example.trainingtesttask.data.repositories.OffersRepositoryImpl
import com.example.trainingtesttask.data.repositories.VacanciesRepositoryImpl
import com.example.trainingtesttask.data.retrofit.BASE_URL
import com.example.trainingtesttask.data.retrofit.MockInterceptor
import com.example.trainingtesttask.data.retrofit.VacanciesAPI
import com.example.trainingtesttask.data.room.VacancyDataBase
import com.example.trainingtesttask.domain.offersRepository.OffersRepository
import com.example.trainingtesttask.domain.offersRepository.VacanciesRepository
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun offersRepository(): OffersRepository

    fun vacanciesRepository(): VacanciesRepository

    fun vacancyDataBase() : VacancyDataBase

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

}

@Module(includes = [NetworkModule::class, DataBaseModule::class])
class AppModule

@Module
class NetworkModule {
    @Provides
    fun provideApi(context: Context): VacanciesAPI {
        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(MockInterceptor(context))
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit.create(VacanciesAPI::class.java)

    }

    @Provides
    fun provideOffersRepository(api: VacanciesAPI): OffersRepository {
        return OffersRepositoryImpl(api)
    }

    @Provides
    fun provideVacanciesRepository(api: VacanciesAPI): VacanciesRepository {
        return VacanciesRepositoryImpl(api)
    }
}

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideVacancyDataBase(context: Context): VacancyDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = VacancyDataBase::class.java,
            name = "VacancyDataBase"
        ).build()
    }
}