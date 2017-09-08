package org.md2k.mcerebrum.communication;
/*
 * Copyright (c) 2016, The University of Memphis, MD2K Center
 * - Syed Monowar Hossain <monowar.hossain@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import org.md2k.mcerebrum.app.Application;
import org.md2k.mcerebrum.core.access.IMCerebrumService;
import org.md2k.mcerebrum.core.access.Info;

import static android.content.Context.BIND_AUTO_CREATE;

public class ServiceCommunication {
    private IMCerebrumService mService;
    private ResponseCallback responseCallback;
    public void start(Context context, String packageName, ResponseCallback responseCallback){
        try {
            this.responseCallback=responseCallback;
            Intent serviceIntent = new Intent()
                    .setComponent(new ComponentName(
                            packageName,
                            packageName + ".ServiceMCerebrum"));
            context.startService(serviceIntent);
            context.bindService(serviceIntent, mConnection, BIND_AUTO_CREATE);
        }catch (Exception ignored){
            responseCallback.onResponse(false);
        }
    }
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d("abc","Service binded!\n");
            mService = IMCerebrumService.Stub.asInterface(service);
            responseCallback.onResponse(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            mService = null;
            // This method is only invoked when the service quits from the other end or gets killed
            // Invoking exit() from the AIDL interface makes the Service kill itself, thus invoking this.
            Log.d("abc","Service disconnected.\n");
        }
    };
    public Info getInfo(){
        try {
            return mService.GetInfo();
        } catch (RemoteException e) {
            return null;
        }
    }
    public void startBackground(){
        try {
            mService.StartBackground();
        } catch (Exception ignored) {
        }
    }
    public void stopBackground(){
        try {
            mService.StopBackground();
        } catch (Exception ignored) {
        }
    }
    public void clear(){
        try {
            mService.Clear();
        }catch (Exception ignored) {
        }
    }
    public void report(){
        try {
            mService.Report();
        }catch (Exception ignored) {
        }
    }
    public void initialize(){
        try {
            mService.Initialize();
        }catch (Exception ignored) {
        }
    }
    public void launch(){
        try {
            mService.Launch();
        }catch (Exception ignored) {
        }
    }
    public void exit(){
        try {
            mService.Exit();
        }catch (Exception ignored) {
        }
    }
    public void configure(){
        try {
            mService.Configure();
        }catch (Exception ignored) {
        }
    }
}
