package com.example.test_application.di.domain

import com.example.test_application.domain.provider.DatabaseProvider
import com.example.test_application.domain.provider.DatabaseProviderImpl
import com.example.test_application.domain.repository.DomainRepository
import com.example.test_application.domain.repository.DomainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(
    ViewModelComponent::class,
    ServiceComponent::class,
    SingletonComponent::class,
)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindDomainRepository(repository: DomainRepositoryImpl): DomainRepository

    @Binds
    abstract fun bindDatabaseProvider(provider: DatabaseProviderImpl): DatabaseProvider
}
