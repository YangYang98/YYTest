package java.com.example.jetpackdemo.data.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Create by Yang Yang on 2023/10/30
 */
@Module
@InstallIn(SingletonComponent::class)
object TestHilt2Module {

    @Provides
    fun provideTestHilt2(): TestHilt2 {
        return TestHilt2()
    }
}