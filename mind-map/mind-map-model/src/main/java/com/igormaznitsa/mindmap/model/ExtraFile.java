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

import java.net.URI;
import java.net.URISyntaxException;

public class ExtraFile extends Extra<URI> implements ExtraLinkable{
  private static final long serialVersionUID = -478916403235887225L;

  private final URI file;
  
  public ExtraFile(final URI file){
    this.file = file;
  }

  public ExtraFile(final String text) throws URISyntaxException {
    this.file = new URI(text);
  }
  
  @Override
  public URI getValue() {
    return file;
  }

  @Override
  public ExtraType getType() {
    return ExtraType.FILE;
  }

  @Override
  public String getAsString() {
    return this.file.toString();
  }

  @Override
  public URI getAsURI() {
    return this.file;
  }

}
