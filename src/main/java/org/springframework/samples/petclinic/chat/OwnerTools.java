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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;

import java.util.List;
import java.util.function.Function;

/**
 * @author Zhiyong Li
 */
@Configuration
public class OwnerTools {

	private final OwnerRepository owners;

	public OwnerTools(OwnerRepository ownerRepository) {
		this.owners = ownerRepository;
	}

	@Bean
	@Description("Query the owners by last name, the owner information include owner id, address, telephone, city, first name and last name"
		+ "\n The owner also include the pets information, include the pet name, pet type and birth"
		+ "\n The pet include serveral visit record, include the visit name and visit date")
	public Function<Request, List<Owner>> queryOwners() {
		return name -> {
			Pageable pageable = PageRequest.of(0, 10);
			return owners.findByLastName(name.lastName, pageable).toList();
		};
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonClassDescription("Owner Query Request")
	public record Request(@JsonProperty(required = true,
		value = "lastName") @JsonPropertyDescription("The Owner last name") String lastName) {
	}

}
