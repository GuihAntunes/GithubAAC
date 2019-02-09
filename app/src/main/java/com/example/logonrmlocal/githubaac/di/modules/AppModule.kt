package com.example.logonrmlocal.githubaac.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.example.logonrmlocal.githubaac.data.local.MeuBancoDeDados
import com.example.logonrmlocal.githubaac.data.local.dao.UserDAO
import com.example.logonrmlocal.githubaac.data.remote.UserService
import com.example.logonrmlocal.githubaac.data.repositories.UserRepository
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): MeuBancoDeDados {
        return Room.databaseBuilder(
                application,
                MeuBancoDeDados::class.java,
                "github.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: MeuBancoDeDados): UserDAO {
        return database.userDAO()
    }

    @Provides
    @Singleton
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }

        @Provides
        @Singleton
        fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("http://api.github.com")
                    .client(client)
                    .build()
        }

        @Provides
        @Singleton
        fun provideUserService(retrofit: Retrofit) : UserService {
            return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        service: UserService,
        userDAO: UserDAO,
        executor: Executor
    ) : UserRepository {
        return UserRepository(
                service,
                userDAO,
                executor
        )
    }
}