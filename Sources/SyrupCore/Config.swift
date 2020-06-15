/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Shopify Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import Foundation
import Files

public struct Config {
	public var schema: URL
	public var shouldGenerateModels: Bool
	public var shouldGenerateSupportFiles: Bool
	public var queries: String
	public var destination: String
	public var supportFilesDestination: String
	public var template: TemplateSpec
	public var project: ProjectSpec
	public var scalars: ScalarSpec
	public var verbose: Bool
	
	public init(schema: URL, shouldGenerateModels: Bool, shouldGenerateSupportFiles: Bool, queries: String, destination: String, supportFilesDestination: String, template: TemplateSpec, project: ProjectSpec, scalars: ScalarSpec, verbose: Bool) {
		self.schema = schema
		self.shouldGenerateModels = shouldGenerateModels
		self.shouldGenerateSupportFiles = shouldGenerateSupportFiles
		self.queries = queries
		self.destination = destination
		self.supportFilesDestination = supportFilesDestination
		self.template = template
		self.project = project
		self.scalars = scalars
		self.verbose = verbose
	}
}
