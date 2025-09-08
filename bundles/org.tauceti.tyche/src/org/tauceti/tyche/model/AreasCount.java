/*
 * Copyright (c) 2025 TauCeti.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributors:
 *     Yang Yang - initial API and implementation
 */
package org.tauceti.tyche.model;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class AreasCount {

	public enum Area {
		N, S, W, E, NW, NE, SW, SE
	}

	protected int N_count;
	protected int S_count;
	protected int W_count;
	protected int E_count;

	protected int NW_count;
	protected int NE_count;
	protected int SW_count;
	protected int SE_count;

	protected int PB_section;

	public AreasCount() {
	}

	/**
	 * 
	 * @param N_count
	 * @param S_count
	 * @param W_count
	 * @param E_count
	 * @param NW_count
	 * @param NE_count
	 * @param SW_count
	 * @param SE_count
	 * @param PB_section
	 */
	public AreasCount(int N_count, int S_count, int W_count, int E_count, int NW_count, int NE_count, int SW_count, int SE_count, int PB_section) {
		this.N_count = N_count;
		this.S_count = S_count;
		this.W_count = W_count;
		this.E_count = E_count;

		this.NW_count = NW_count;
		this.NE_count = NE_count;
		this.SW_count = SW_count;
		this.SE_count = SE_count;

		this.PB_section = PB_section;
	}

	public int getN_count() {
		return this.N_count;
	}

	public void setN_count(int n_count) {
		this.N_count = n_count;
	}

	public int getS_count() {
		return this.S_count;
	}

	public void setS_count(int s_count) {
		this.S_count = s_count;
	}

	public int getW_count() {
		return this.W_count;
	}

	public void setW_count(int w_count) {
		this.W_count = w_count;
	}

	public int getE_count() {
		return this.E_count;
	}

	public void setE_count(int e_count) {
		this.E_count = e_count;
	}

	public int getNW_count() {
		return this.NW_count;
	}

	public void setNW_count(int nW_count) {
		this.NW_count = nW_count;
	}

	public int getNE_count() {
		return this.NE_count;
	}

	public void setNE_count(int nE_count) {
		this.NE_count = nE_count;
	}

	public int getSW_count() {
		return this.SW_count;
	}

	public void setSW_count(int sW_count) {
		this.SW_count = sW_count;
	}

	public int getSE_count() {
		return this.SE_count;
	}

	public void setSE_count(int sE_count) {
		this.SE_count = sE_count;
	}

	public int getPB_section() {
		return this.PB_section;
	}

	public void setPB_section(int pB_section) {
		this.PB_section = pB_section;
	}

	@Override
	public String toString() {
		return "AreasCount [N_count=" + N_count + ", S_count=" + S_count + ", W_count=" + W_count + ", E_count=" + E_count + ", NW_count=" + NW_count + ", NE_count=" + NE_count + ", SW_count=" + SW_count + ", SE_count=" + SE_count + ", PB_section=" + PB_section + "]";
	}
}
