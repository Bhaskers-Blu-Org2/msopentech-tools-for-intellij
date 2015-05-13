/**
 * Copyright 2014 Microsoft Open Technologies Inc.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoftopentechnologies.tooling.msservices.helpers;

import com.google.common.util.concurrent.SettableFuture;
import com.microsoftopentechnologies.tooling.msservices.helpers.aadauth.BrowserLauncher;
import com.microsoftopentechnologies.tooling.msservices.model.storage.Queue;
import com.microsoftopentechnologies.tooling.msservices.model.storage.StorageAccount;
import com.microsoftopentechnologies.tooling.msservices.model.storage.StorageServiceTreeItem;
import com.microsoftopentechnologies.tooling.msservices.serviceexplorer.Node;
import org.apache.commons.lang3.tuple.Pair;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IDEHelper {
    void openFile(@NotNull File file, @NotNull Node node);

    void runInBackground(@Nullable Object project, @NotNull String name, boolean canBeCancelled,
                         boolean isIndeterminate, @Nullable String indicatorText,
                         Runnable runnable);

    void saveFile(@NotNull File file, @NotNull ByteArrayOutputStream buff, @NotNull Node node);

    void replaceInFile(@NotNull Object module, @NotNull Pair<String, String>... replace);

    void copyJarFiles2Module(@NotNull Object moduleObject, @NotNull File zipFile, @NotNull String zipPath)
            throws IOException;

    boolean isFileEditing(@NotNull Object projectObject, @NotNull File file);

    <T extends StorageServiceTreeItem> void openItem(@NotNull Object projectObject,
                                                     @Nullable StorageAccount storageAccount,
                                                     @NotNull T blobContainer,
                                                     @Nullable String itemType,
                                                     @NotNull String itemName,
                                                     @Nullable String iconName);

    @Nullable
    <T extends StorageServiceTreeItem> Object getOpenedFile(@NotNull Object projectObject,
                                                            @NotNull StorageAccount storageAccount,
                                                            @NotNull T blobContainer);

    void closeFile(@NotNull Object projectObject, @NotNull Object openedFile);

    void refreshQueue(@NotNull Object projectObject, @NotNull StorageAccount storageAccount, @NotNull Queue queue);

    void invokeAuthLauncherTask(@Nullable Object projectObject, @NotNull BrowserLauncher browserLauncher,
                                @NotNull String windowTitle);

    void invokeBackgroundLoader(@Nullable Object projectObject, @NotNull Node node,
                                @NotNull SettableFuture<List<Node>> future, @NotNull String name);

    void invokeLater(@NotNull Runnable runnable);

    void invokeAndWait(@NotNull Runnable runnable);

    void executeOnPooledThread(@NotNull Runnable runnable);

    @Nullable
    String getProperty(@NotNull String name);

    @NotNull
    String getProperty(@NotNull String name, @NotNull String defaultValue);

    void setProperty(@NotNull String name, @NotNull String value);

    void unsetProperty(@NotNull String name);

    boolean isPropertySet(@NotNull String name);

    @NotNull
    String promptForOpenSSLPath();
}