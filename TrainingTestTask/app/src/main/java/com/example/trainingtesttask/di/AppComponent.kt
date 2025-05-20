package com.example.trainingtesttask.di

import android.content.Context
import com.example.trainingtesttask.data.repositories.OffersRepositoryImpl
import com.example.trainingtesttask.data.retrofit.BASE_URL
import com.example.trainingtesttask.data.retrofit.MockInterceptor
import com.example.trainingtesttask.data.retrofit.VacanciesAPI
import com.example.trainingtesttask.domain.offersRepository.OffersRepository
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Component(modules = [AppModule::class])
interface AppComponent {

    fun offersRepository(): OffersRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

}

@Module
class AppModule {

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

}