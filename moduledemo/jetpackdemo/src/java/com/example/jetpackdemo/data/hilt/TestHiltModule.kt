package java.com.example.jetpackdemo.data.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Create by Yang Yang on 2023/10/30
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class TestHiltModule {

    @Binds
    abstract fun bindTestHilt(impl: TestHilt): ITestHilt
}