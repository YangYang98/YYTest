package java.com.example.jetpackdemo.data.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


/**
 * Create by Yang Yang on 2023/10/30
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class TestHiltModule {

    @Binds
    @ITestHilt1
    abstract fun bindTestHilt(impl: TestHilt): ITestHilt

    @Binds
    @ITestHilt2
    abstract fun bindTestHilt3(impl: TestHilt3): ITestHilt
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ITestHilt1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ITestHilt2