package com.android.internal.telephony;

import android.os.Bundle;
import java.util.List;
import android.telephony.NeighboringCellInfo;

/**
 * Interface used to interact with the phone.  Mostly this is used by the 
 * TelephonyManager class.  A few places are still using this directly.
 * Please clean them up if possible and use TelephonyManager insteadl.
 *
 * {@hide}
 */
interface ITelephony {

    boolean endCall();
    void answerRingingCall();

}