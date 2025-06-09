/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.chat;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;

import java.util.Collection;
import java.util.function.Function;

/**
 * @author Zhiyong Li
 */
@Configuration
public class VetTools {

	private final VetRepository vetRepository;

	public VetTools(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Bean
	@Description("return list of Vets, include their specialties")
	public Function<Request, Collection<Vet>> queryVets() {
		return request -> {
			return vetRepository.findAll();
		};
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonClassDescription("Query vets request")
	public record Request(@JsonProperty(required = false,
			value = "specialty") @JsonPropertyDescription("The specialty") String specialty) {
	}

}
