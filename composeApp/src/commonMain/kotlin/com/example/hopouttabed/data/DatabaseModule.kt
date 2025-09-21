package com.example.hopouttabed.data
//
//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            AppDatabase::class.java,
//            "hop_out_of_bed_db"
//        ).fallbackToDestructiveMigration(true).build() // cannot deploy with fallbackToDestructiveMigration
//    }
//
//    @Provides
//    fun provideAlarmDao(db: AppDatabase): AlarmDao = db.alarmDao()
//}