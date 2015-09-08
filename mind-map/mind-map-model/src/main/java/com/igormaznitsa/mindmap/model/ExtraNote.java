/*
 * Copyright 2015 Igor Maznitsa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.igormaznitsa.mindmap.model;

public class ExtraNote extends Extra<String> {
  private static final long serialVersionUID = 8612886872756838947L;

  private final String text;
  
  public ExtraNote(final String text){
    this.text = text;
  }
  
  @Override
  public String getValue() {
    return this.text;
  }

  @Override
  public ExtraType getType() {
    return ExtraType.NOTE;
  }

  @Override
  public String getAsString() {
    return this.text;
  }

}
