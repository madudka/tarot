package com.madudka.tarot.view

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.madudka.tarot.model.room.SingletonHolder
import java.time.LocalDate

private const val SETTINGS = "SETTINGS"
private const val DAY_CARD_DATE = "DAY_CARD_DATE"
private const val VERIFY_DATE = "VERIFY_DATE"
class Settings(context: Context){

    companion object : SingletonHolder<Settings, Context>({
        Settings(it.applicationContext)
    })

    private object Crypto {
        private val keyGenParamSpec = KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .build()

        fun masterKey(context: Context) = MasterKey.Builder(context)
            .setKeyGenParameterSpec(keyGenParamSpec)
            .build()
    }

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        SETTINGS,
        Crypto.masterKey(context),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

    private val editor = sharedPreferences.edit()

    private inline fun editSettings(operation: (SharedPreferences.Editor) -> Unit) {
        operation(editor)
        editor.apply()
    }

    fun clear() = editSettings { it.clear() }

    var dayCardDate
        get() = sharedPreferences.getLong(DAY_CARD_DATE,
            LocalDate.now().minusDays(1).toEpochDay())
        set(value) {
            editSettings {
                it.putLong(DAY_CARD_DATE, value)
            }
        }

    var verifyDate
        get() = sharedPreferences.getLong(VERIFY_DATE,
            LocalDate.now().minusDays(1).toEpochDay())
        set(value) {
            editSettings {
                it.putLong(VERIFY_DATE, value)
            }
        }
}